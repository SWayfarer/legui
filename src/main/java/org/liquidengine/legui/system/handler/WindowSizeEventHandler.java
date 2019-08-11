package org.liquidengine.legui.system.handler;

import java.util.Collections;
import java.util.List;
import org.liquidengine.legui.core.component.Component;
import org.liquidengine.legui.core.component.Frame;
import org.liquidengine.legui.core.component.Layer;
import org.liquidengine.legui.core.event.WindowSizeEvent;
import org.liquidengine.legui.core.listener.processor.EventProcessor;
import org.liquidengine.legui.system.context.Context;
import org.liquidengine.legui.system.event.SystemWindowSizeEvent;

/**
 * Created by ShchAlexander on 2/2/2017.
 */
public class WindowSizeEventHandler implements SystemEventHandler<SystemWindowSizeEvent> {

    @Override
    public void handle(SystemWindowSizeEvent event, Frame frame, Context context) {
        List<Layer> layers = frame.getAllLayers();
        Collections.reverse(layers);
        for (Layer layer : layers) {
            if (!layer.isVisible() || !layer.isEnabled()) {
                continue;
            }
            pushEvent(layer, event, context, frame);
        }
    }

    private void pushEvent(Component component, SystemWindowSizeEvent event, Context context, Frame frame) {
        if (!component.isVisible() || !component.isEnabled()) {
            return;
        }
        EventProcessor.getInstance().pushEvent(new WindowSizeEvent(component, context, frame, event.width, event.height));
        List<Component> childComponents = component.getChildComponents();
        for (Component child : childComponents) {
            pushEvent(child, event, context, frame);
        }
    }
}
