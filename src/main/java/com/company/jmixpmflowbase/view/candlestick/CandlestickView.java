package com.company.jmixpmflowbase.view.candlestick;


import com.company.jmixpmflowbase.app.StockDataService;
import com.company.jmixpmflowbase.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.chartsflowui.component.Chart;
import io.jmix.chartsflowui.data.item.MapDataItem;
import io.jmix.chartsflowui.kit.component.model.DataSet;
import io.jmix.chartsflowui.kit.data.chart.ListChartItems;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "candlestick-view", layout = MainView.class)
@ViewController("CandlestickView")
@ViewDescriptor("candlestick-view.xml")
public class CandlestickView extends StandardView {
    @ViewComponent
    private Chart chart;
    @Autowired
    private StockDataService stockDataService;

    @Subscribe
    public void onInit(final InitEvent event) {
        chart.setDataSet(
                new DataSet().withSource(
                        new DataSet.Source<MapDataItem>()
                                .withDataProvider(new ListChartItems<>(stockDataService.fetchData()))
                                .withCategoryField("Date")
                                .withValueFields("Open", "Close", "Low", "High", "Volume")
                )
        );
    }
    
    
}