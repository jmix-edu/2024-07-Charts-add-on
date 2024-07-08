package com.company.jmixpmflowbase.view.projectstats;


import com.company.jmixpmflowbase.app.ProjectStatsService;
import com.company.jmixpmflowbase.entity.Project;
import com.company.jmixpmflowbase.entity.ProjectStats;
import com.company.jmixpmflowbase.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.chartsflowui.kit.component.event.ChartClickEvent;
import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "project-stats-view", layout = MainView.class)
@ViewController("ProjectStatsView")
@ViewDescriptor("project-stats-view.xml")
public class ProjectStatsView extends StandardView {

    @Autowired
    private ProjectStatsService projectStatService;
    @Autowired
    private Notifications notifications;
    @ViewComponent
    private CollectionContainer<ProjectStats> projectStatsDc;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private DialogWindows dialogWindows;
    @ViewComponent
    private CollectionLoader<ProjectStats> projectStatsDl;

    @Install(to = "projectStatsDl", target = Target.DATA_LOADER)
    private List<ProjectStats> projectStatsDlLoadDelegate(LoadContext<ProjectStats> loadContext) {
        return projectStatService.fetchProjectStatistics();
    }

    @Subscribe("chart")
    public void onChartChartClick(final ChartClickEvent event) {
        ProjectStats projectStats = projectStatsDc.getItems().stream()
                .filter(p -> event.getDetail().getName().equals(p.getProjectName()))
                .findAny()
                .orElseThrow();

        Project selectedProject = dataManager.load(Project.class)
                .id(projectStats.getId())
                .one();

        dialogWindows.detail(this, Project.class)
                .editEntity(selectedProject)
                .withAfterCloseListener(__ -> projectStatsDl.load())
                .open();

    }
}