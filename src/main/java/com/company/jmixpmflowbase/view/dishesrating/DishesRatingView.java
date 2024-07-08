package com.company.jmixpmflowbase.view.dishesrating;


import com.company.jmixpmflowbase.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.chartsflowui.component.Chart;
import io.jmix.chartsflowui.data.item.SimpleDataItem;
import io.jmix.chartsflowui.kit.component.model.DataSet;
import io.jmix.chartsflowui.kit.data.chart.ListChartItems;
import io.jmix.flowui.view.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Route(value = "dishes-rating-view", layout = MainView.class)
@ViewController("DishesRatingView")
@ViewDescriptor("dishes-rating-view.xml")
public class DishesRatingView extends StandardView {

    @ViewComponent
    private Chart chart;

    private final Random random = new Random();

    private final List<String> dishList = List.of(
            "Pasta", "Tortilla", "Yakiniku", "Croissant",
            "Tacos", "Ramen", "Burger", "Pizza"
    );

    @Subscribe
    public void onInit(InitEvent event) {
        List<SimpleDataItem> items = new ArrayList<>();
        for (int i = 0; i < dishList.size(); i++) {
            items.add(generateDishRating(dishList.get(i), i));
        }

        chart.setDataSet(
                new DataSet().withSource(
                        new DataSet.Source<SimpleDataItem>()
                                .withDataProvider(new ListChartItems<>(items))
                                .withCategoryField("dishName")
                                .withValueFields("tasteRating", "servingRating", "price")
                )
        );
    }

    private SimpleDataItem generateDishRating(String dishName, int i) {
        Double tasteRating = random.nextInt(i + 1, 20) / 10.0;
        Double servingRating = random.nextInt(i + 1, 10) / 10.0;
        Integer price = random.nextInt(i + 1, 10);

        return new SimpleDataItem(new DishRating(dishName, tasteRating, servingRating, price));
    }

    public static class DishRating {

        private final String dishName;
        private final Double tasteRating;
        private final Double servingRating;
        private final Integer price;

        public DishRating(String dishName, Double tasteRating, Double servingRating, Integer price) {
            this.dishName = dishName;
            this.tasteRating = tasteRating;
            this.servingRating = servingRating;
            this.price = price;
        }

        public String getDishName() {
            return dishName;
        }

        public Double getTasteRating() {
            return tasteRating;
        }

        public Double getServingRating() {
            return servingRating;
        }

        public Integer getPrice() {
            return price;
        }
    }
}