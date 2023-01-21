package steps;

import endpoints.UiEndpoints;
import io.qameta.allure.Step;
import pages.ProjectCreatePage;
import pages.ProjectPage;
import pages.ProjectsPage;

public class CreateProjectStep {
    ProjectPage projectPage;

    @Step
    public ProjectPage createProject(String projectName, String projectCode) {

        projectPage = new ProjectsPage(false, UiEndpoints.PROJECTS)
                .createProjectButtonClick()
                .setProjectName(projectName)
                .setProjectCode(projectCode)
                .clickCreateProjectSuccessBtn();
        return projectPage = new ProjectPage(false,String.format(UiEndpoints.PROJECT,projectCode));
    }

    @Step
    public ProjectCreatePage incorrectProjectCreation(String projectName) {

        ProjectsPage projectsPage = new ProjectsPage(false, UiEndpoints.PROJECTS);
        projectsPage
                .createProjectButtonClick()
                .setProjectName(projectName)
                .clickCreateProjectBtn();
        return new ProjectCreatePage(false,UiEndpoints.CREATE_PROJECT);
    }
}
