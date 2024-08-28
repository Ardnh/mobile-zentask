package com.example.zentask.data.model

data class Category(
    val id: String,
    val name: String
)

data class CreateCategoryRequest(
    val name: String,
)

data class UpdateCategoryRequest(
    val name: String
)

data class CategoryPagination(
    val currentPage: Int,
    val itemsPerPage: Int,
    val totalItems: Int,
    val totalPages: Int
)

data class CategoryFilters(
    val categoryName: String,
    val sortBy: String,
    val sortDirection: String,
)

data class CategoryResponse(
    val items: List<Category>,
    val pagination: CategoryPagination,
    val filters: CategoryFilters
)

data class CategoryQueryParams(
    val page: Int,
    val pageSize: Int,
    val categoryName: String,
    val sortDirection: String,
    val sortBy: String,
)
