package com.example.zentask.data.repository

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
import com.example.zentask.data.source.remote.ProjectRemoteDataSource
import javax.inject.Inject

interface ProjectRepository{
    suspend fun createProject(req: CreateProjectRequest) : Result<CommonResponse?>
    suspend fun updateProject(id: String, req: UpdateProjectRequest) : Result<CommonResponse?>
    suspend fun deleteProject(id: String) : Result<CommonResponse?>
    suspend fun getAllProjects(params: ProjectQueryParams ) : Result<ResponseWithData<ProjectResponse>?>
    suspend fun  createProjectItem(req: CreateProjectItemRequest) : Result<CommonResponse?>
    suspend fun updateProjectItem(id: String, req: UpdateProjectItemRequest) : Result<CommonResponse?>
    suspend fun deleteProjectItem(id: String) : Result<CommonResponse?>
    suspend fun getAllProjectItem(projectId: String, params: ProjectItemQueryParams) : Result<ResponseWithData<ProjectItemResponse>?>
}

class ProjectRepositoryImpl @Inject constructor(private val apiService: ProjectRemoteDataSource) : ProjectRepository {

    override suspend fun createProject(req: CreateProjectRequest): Result<CommonResponse?> {
        return apiService.createProject(req)
    }

    override suspend fun updateProject(id: String, req: UpdateProjectRequest): Result<CommonResponse?> {
        return apiService.updateProject(id, req)
    }

    override suspend fun deleteProject(id: String): Result<CommonResponse?> {
        return apiService.deleteProject(id)
    }

    override suspend fun getAllProjects(params: ProjectQueryParams): Result<ResponseWithData<ProjectResponse>?> {
        return apiService.getAllProjects(params)
    }

    override suspend fun createProjectItem(req: CreateProjectItemRequest): Result<CommonResponse?> {
        return apiService.createProjectItem(req)
    }

    override suspend fun updateProjectItem(id: String, req: UpdateProjectItemRequest): Result<CommonResponse?> {
        return apiService.updateProjectItem(id, req)
    }

    override suspend fun deleteProjectItem(id: String): Result<CommonResponse?> {
        return apiService.deleteProjectItem(id)
    }

    override suspend fun getAllProjectItem(projectId: String, params: ProjectItemQueryParams): Result<ResponseWithData<ProjectItemResponse>?> {
        return apiService.getAllProjectItem(projectId, params)
    }
}
