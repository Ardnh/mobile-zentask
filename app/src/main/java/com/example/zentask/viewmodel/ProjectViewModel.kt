package com.example.zentask.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zentask.data.model.ProjectQueryParams
import com.example.zentask.data.model.ProjectRequest
import com.example.zentask.data.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(private val repository: ProjectRepository) : ViewModel() {

    // Instance

    // State
    private val _projectRequest = MutableStateFlow<ProjectRequest>(ProjectRequest())
    private val _projectQueryParams = MutableStateFlow<ProjectQueryParams>(ProjectQueryParams())
    private val _onSuccessMessage = MutableStateFlow<String?>(null)
    private val _onErrorMessage = MutableStateFlow<String?>(null)
    private val _searchProjectRequest = MutableStateFlow<String>("")

    // Getter
    val searchProjectRequest: StateFlow<String> = _searchProjectRequest
    val projectQueryParams: StateFlow<ProjectQueryParams> = _projectQueryParams

    // Actions
    fun updateSearchField(value: String){
        _searchProjectRequest.value = value
    }
    fun updateField(fieldName: String, value: Any) {
        _projectRequest.update { currentRequest ->
            when (fieldName) {
                "categoryId" -> currentRequest.copy(categoryId = value as String)
                "userId" -> currentRequest.copy(userId = value as String)
                "name" -> currentRequest.copy(name = value as String)
                "description" -> currentRequest.copy(description = value as String)
                "budget" -> currentRequest.copy(budget = value as Int)
                else -> currentRequest
            }
        }
    }

    suspend fun getProject() {

        viewModelScope.launch {
            val result = repository.getAllProjects(_projectQueryParams.value)
            result.fold(
                onSuccess = {
                    Log.d("ProjectViewModel", "Successfully get project: $it")
                    _onSuccessMessage.value = "Successfully get project!"
                },
                onFailure = {
                    Log.e("ProjectViewModel", "Failed to create project: ${it.message}")
                    _onErrorMessage.value = "Failed to get project: ${it.message}"
                }
            )
        }
    }
    suspend fun createProject() {
        viewModelScope.launch {
            Log.d("ProjectViewModel", "Creating project with request: ${_projectRequest.value}")

            val result = repository.createProject(_projectRequest.value)
            result.fold(
                onSuccess = {
                    Log.d("ProjectViewModel", "Project created successfully: $it")
                    _onSuccessMessage.value = "Project created!"
                },
                onFailure = {
                    Log.e("ProjectViewModel", "Failed to create project: ${it.message}")
                    _onErrorMessage.value = "Failed to create project: ${it.message}"
                }
            )
        }
    }

    suspend fun updateProject(projectId: String) {
        viewModelScope.launch {
            Log.d("ProjectViewModel", "Updating project with ID: $projectId and request: ${_projectRequest.value}")

            val result = repository.updateProject(projectId, _projectRequest.value)
            result.fold(
                onSuccess = {
                    Log.d("ProjectViewModel", "Project updated successfully: $it")
                    _onSuccessMessage.value = "Project updated!"
                },
                onFailure = {
                    Log.e("ProjectViewModel", "Failed to update project with ID: $projectId, Error: ${it.message}")
                    _onErrorMessage.value = "Failed to update project: ${it.message}"
                }
            )
        }
    }

    suspend fun deleteProject(projectId: String){
        viewModelScope.launch {
            Log.d("ProjectViewModel", "Updating project with ID: $projectId and request: ${_projectRequest.value}")


            val result = repository.deleteProject(projectId)
            result.fold(
                onSuccess = {
                    Log.d("ProjectViewModel", "Project deleted successfully: $it")
                    _onSuccessMessage.value = "Project deleted!"
                },
                onFailure = {
                    Log.e("ProjectViewModel", "Failed to delete project with ID: $projectId, Error: ${it.message}")
                    _onErrorMessage.value = "Failed to delete project: ${it.message}"
                }
            )

        }
    }

    fun setProjectQueryParams(params: ProjectQueryParams){
        _projectQueryParams.value = params
    }

}