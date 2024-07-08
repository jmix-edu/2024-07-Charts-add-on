package com.company.jmixpmflowbase.view.piechart;


import com.company.jmixpmflowbase.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.chartsflowui.component.Chart;
import io.jmix.chartsflowui.data.item.MapDataItem;
import io.jmix.chartsflowui.kit.component.model.DataSet;
import io.jmix.chartsflowui.kit.data.chart.ListChartItems;
import io.jmix.flowui.view.*;

import java.util.Map;

@Route(value = "pie-chart-view", layout = MainView.class)
@ViewController("PieChartView")
@ViewDescriptor("pie-chart-view.xml")
public class PieChartView extends StandardView {

    @ViewComponent
    private Chart chart;

    @Subscribe
    public void onInit(InitEvent event) {
        ListChartItems<MapDataItem> items = new ListChartItems<>(
                new MapDataItem(Map.of("name", "Search Engine", "value", 1048)),
                new MapDataItem(Map.of("name", "Direct", "value", 735)),
                new MapDataItem(Map.of("name", "Email", "value", 580)),
                new MapDataItem(Map.of("name", "Union Ads", "value", 484)),
                new MapDataItem(Map.of("name", "Video Ads", "value", 300))
        );

        chart.setDataSet(
                new DataSet().withSource(
                        new DataSet.Source<MapDataItem>()
                                .withDataProvider(items)
                                .withCategoryField("name")
                                .withValueField("value")
                )
        );
    }
}