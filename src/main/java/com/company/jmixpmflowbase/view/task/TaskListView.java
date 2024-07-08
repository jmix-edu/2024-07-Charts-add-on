package com.company.jmixpmflowbase.view.task;

import com.company.jmixpmflowbase.entity.Task;

import com.company.jmixpmflowbase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.chartsflowui.component.Chart;
import io.jmix.chartsflowui.data.item.SimpleDataItem;
import io.jmix.chartsflowui.kit.component.model.DataSet;
import io.jmix.chartsflowui.kit.data.chart.ListChartItems;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;

import java.util.List;

@Route(value = "tasks", layout = MainView.class)
@ViewController("Task_.list")
@ViewDescriptor("task-list-view.xml")
@LookupComponent("tasksDataGrid")
@DialogMode(width = "64em")
public class TaskListView extends StandardListView<Task> {

    @ViewComponent
    private Chart tasksEffortsChart;

    @Subscribe(id = "tasksDc", target = Target.DATA_CONTAINER)
    public void onTasksDcCollectionChange(final CollectionContainer.CollectionChangeEvent<Task> event) {
        List<SimpleDataItem> items = event.getSource().getItems()
                .stream()
                .map(this::tasksMapper)
                .toList();

        tasksEffortsChart.setDataSet(
                new DataSet().withSource(
                        new DataSet.Source<SimpleDataItem>()
                                .withDataProvider(new ListChartItems<>(items))
                                .withCategoryField("name")
                                .withValueField("estimatedEfforts")
                )
        );
    }

    private SimpleDataItem tasksMapper(Task task) {
        TaskEfforts taskEfforts = new TaskEfforts(task.getName(), task.getEstimatedEfforts());
        return new SimpleDataItem(taskEfforts);
    }

    public static class TaskEfforts {

        private String name;
        private Integer estimatedEfforts;

        public TaskEfforts(String name, Integer estimatedEfforts) {
            this.name = name;
            this.estimatedEfforts = estimatedEfforts;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getEstimatedEfforts() {
            return estimatedEfforts;
        }

        public void setEstimatedEfforts(Integer estimatedEfforts) {
            this.estimatedEfforts = estimatedEfforts;
        }
    }
}