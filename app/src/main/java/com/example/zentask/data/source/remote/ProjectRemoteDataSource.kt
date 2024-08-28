package com.example.zentask.data.source.remote

import com.example.zentask.data.model.CommonResponse
import com.example.zentask.data.model.CreateProjectItemRequest
import com.example.zentask.data.model.CreateProjectRequest
import com.example.zentask.data.model.ProjectItemQueryParams
import com.example.zentask.data.model.ProjectItemResponse
import com.example.zentask.data.model.ProjectQueryParams
import com.example.zentask.data.model.ProjectResponse
import com.example.zentask.data.model.ResponseWithData
import com.example.zentask.data.model.UpdateProjectItemRequest
import com.example.zentask.data.model.UpdateProjectRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

interface ProjectApiService {
    @POST("/projects")
    suspend fun createProject(@Body req: CreateProjectRequest): Response<CommonResponse>

    @PUT("/projects/{id}")
    suspend fun updateProject(@Path("id") id: String,  @Body req: UpdateProjectRequest) : Response<CommonResponse>

    @DELETE("/projects/{id}")
    suspend fun deleteProject(@Path("id")  id: String) : Response<CommonResponse>

    @GET("/projects")
    suspend fun getAllProjects(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("projectName") projectName: String,
        @Query("sortDirection") sortDirection: String,
        @Query("categoryName") categoryName: String,
        @Query("sortBy") sortBy: String
    ) : Response<ResponseWithData<ProjectResponse>>

    @POST("/project-item")
    suspend fun createProjectItem(@Body req: CreateProjectItemRequest): Response<CommonResponse>

    @PUT("/project-item/{id}")
    suspend fun updateProjectItem(@Path("id") id: String, @Body req: UpdateProjectItemRequest): Response<CommonResponse>

    @DELETE("/project-item/{id}")
    suspend fun deleteProjectItem(@Path("id") id: String): Response<CommonResponse>

    @GET("/project-item")
    suspend fun getAllProjectItem(
        @Path("project_id") id: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("projectName") projectName: String,
        @Query("sortDirection") sortDirection: String,
        @Query("sortBy") sortBy: String
    ): Response<ResponseWithData<ProjectItemResponse>>
}

class ProjectRemoteDataSource @Inject constructor(private val projectApiService : ProjectApiService) {

    suspend fun createProject(req: CreateProjectRequest): Result<CommonResponse?> {
        return handleApiCall { projectApiService.createProject(req) }
    }

    // Update an existing project by ID
    suspend fun updateProject(id: String, req: UpdateProjectRequest): Result<CommonResponse?> {
        return handleApiCall { projectApiService.updateProject(id, req) }
    }

    // Delete a project by ID
    suspend fun deleteProject(id: String): Result<CommonResponse?> {
        return handleApiCall { projectApiService.deleteProject(id) }
    }

    // Get all projects with filtering and pagination
    suspend fun getAllProjects(req: ProjectQueryParams): Result<ResponseWithData<ProjectResponse>?> {
        return handleApiCall { projectApiService.getAllProjects(
            page = req.page, pageSize = req.pageSize, projectName = req.projectName, sortDirection = req.sortDirection, categoryName = req.categoryName, sortBy = req.sortBy
        )}
    }

    // Create a new project item
    suspend fun createProjectItem(req: CreateProjectItemRequest): Result<CommonResponse?> {
        return handleApiCall { projectApiService.createProjectItem(req) }
    }

    // Update an existing project item by ID
    suspend fun updateProjectItem(id: String, req: UpdateProjectItemRequest): Result<CommonResponse?> {
        return handleApiCall{ projectApiService.updateProjectItem(id, req) }
    }

    // Delete a project item by ID
    suspend fun deleteProjectItem(id: String): Result<CommonResponse?> {
        return handleApiCall { projectApiService.deleteProjectItem(id) }
    }

    // Get project items with filtering and pagination
    suspend fun getAllProjectItem(projectId: String, req: ProjectItemQueryParams): Result<ResponseWithData<ProjectItemResponse>?> {
        return handleApiCall { projectApiService.getAllProjectItem(
            id = projectId, page = req.page, pageSize = req.pageSize, projectName = req.projectName, sortDirection = req.sortDirection, sortBy = req.sortBy
        ) }
    }

    private suspend fun <T> handleApiCall(apiCall: suspend () -> Response<T>): Result<T?> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception("API call failed with error ${response.code()} | ${response.message()}"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}