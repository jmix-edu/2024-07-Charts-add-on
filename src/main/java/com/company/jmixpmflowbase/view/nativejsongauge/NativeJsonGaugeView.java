package com.company.jmixpmflowbase.view.nativejsongauge;


import com.company.jmixpmflowbase.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "native-json-gauge-view", layout = MainView.class)
@ViewController("NativeJsonGaugeView")
@ViewDescriptor("native-json-gauge-view.xml")
public class NativeJsonGaugeView extends StandardView {
}