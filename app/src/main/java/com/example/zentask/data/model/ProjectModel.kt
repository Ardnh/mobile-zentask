package com.example.zentask.data.model

data class Project(
    val id: String,
    val categoryId: String,
    val userId: String,
    val name: String,
    val description: String,
    val budget: Int,
)

data class ProjectItem(
    val id: String,
    val projectId: String,
    val name: String,
    val budgetItem: Int,
    val status: Boolean,
)

data class CreateProjectRequest(
    val categoryId: String,
    val userId: String,
    val name: String,
    val description: String,
    val budget: Int,
)

data class UpdateProjectRequest(
    val categoryId: String,
    val userId: String,
    val name: String,
    val description: String,
    val budget: Int,
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
    val items: Project,
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
    val page: Int,
    val pageSize: Int,
    val projectName: String,
    val sortDirection: String,
    val categoryName: String,
    val sortBy: String,
)

data class ProjectItemQueryParams(
    val page: Int,
    val pageSize: Int,
    val projectName: String,
    val sortDirection: String,
    val sortBy: String,
)