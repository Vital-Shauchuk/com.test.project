package endpoints.api;

public interface ProjectsEndpoints {

    String GET_ALL_PROJECTS = "v1/project";
    String ADD_PROJECT ="v1/project";
    String GET_PROJECT ="v1/project/%s";
    String DELETE_PROJECT = "v1/project/%s";
    String UPDATE_PROJECT = "v1/project/%s";
    String INVALID_ENDPOINT = "v1/proj";
}
