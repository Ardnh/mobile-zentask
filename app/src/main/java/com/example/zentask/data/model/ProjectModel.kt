package com.example.zentask.data.model

import com.google.gson.annotations.SerializedName

//data class Project(
//    val id: String,
//    @SerializedName("category_id") val categoryId: String,
//    @SerializedName("user_id") val userId: String,
//    val name: String,
//    val description: String,
//    val budget: Int,
//)

data class Project(
    val id: String,
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("user_id")
    val userId: String,
    val name: String,
    val description: String,
    val budget: Long,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    val category: Category,
)

data class ProjectItem(
    val id: String,
    @SerializedName("project_id") val projectId: String,
    val name: String,
    @SerializedName("budget_item") val budgetItem: Int,
    val status: Boolean,
)

data class ProjectRequest(
    val categoryId: String = "",
    val userId: String = "",
    val name: String = "",
    val description: String = "",
    val budget: Int = 0,
)

data class CreateProjectItemRequest(
    val projectId: String,
    val name: String,
    val budgetItem: Int,
    val status: Boolean,
)

data class UpdateProjectItemRequest(
    val projectId: String,
    val name: String,
    val budgetItem: Int,
    val status: Boolean,
)

data class ProjectItemCombine(
    val project: Project,
    val items: List<ProjectItem>,
)

data class ProjectPagination(
    val currentPage: Int,
    val itemsPerPage: Int,
    val totalItems: Int,
    val totalPages: Int
)

data class ProjectFilters(
    val categoryName: String,
    val projectName: String,
    val sortBy: String,
    val sortDirection: String,
)

data class ProjectResponse(
    val code: Int,
    val message: String,
    val processTime: Int,
    val items: List<Project>,
    val pagination: ProjectPagination,
    val filters: ProjectFilters,
)

data class ProjectResponseData(
    val items: List<Project>,
    val pagination: ProjectPagination,
    val filters: ProjectFilters,
)

data class ProjectItemFilters(
    val projectName: String,
    val sortBy: String,
    val sortDirection: String,
)

data class ProjectItemResponse(
    val items: ProjectItemCombine,
    val pagination: ProjectPagination,
    val filters: ProjectItemFilters,
)

data class ProjectQueryParams(
    val page: Int = 1,
    val pageSize: Int = 50,
    val projectName: String = "",
    val sortDirection: String = "asc",
    val categoryName: String = "",
    val sortBy: String = "date",
)

data class ProjectItemQueryParams(
    val page: Int,
    val pageSize: Int,
    val projectName: String,
    val sortDirection: String,
    val sortBy: String,
)