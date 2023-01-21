package endpoints;

public interface UiEndpoints {

    String LOGIN = "login";
    String CREATE_PROJECT = "project/create";
    String PROJECTS = "projects";
    String PROJECT_SETTINGS = "project/%s/settings/general";
    String PROJECT_DELETE_CONFIRMATION = "project/%s/delete";
    String PROJECT = "project/%s";
    String SUITE = "project/%s?suite=%d";
    String CASE_IMPORT = "case/%s/import";
    String CASE_CREATE = "case/%s/create";
    String CASE_EDIT = "case/%s/edit/1";
    String WORKSPACE_MEMBERS = "workspace";
}
