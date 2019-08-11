package org.liquidengine.legui.system.renderer;

import java.util.concurrent.atomic.AtomicBoolean;
import org.liquidengine.legui.core.component.Component;
import org.liquidengine.legui.core.style.Border;
import org.liquidengine.legui.system.context.Context;

/**
 * Border renderer base.
 */
public abstract class BorderRenderer<B extends Border> {

    private AtomicBoolean initialized = new AtomicBoolean(false);

    public void initialize() {
        // should be overrided
    }

    public void render(B border, Component component, Context context) {
        if (!initialized.getAndSet(true)) {
            initialize();
        } else {
            renderBorder(border, component, context);
        }
    }

    public abstract void renderBorder(B border, Component component, Context context);

    public void destroy() {
        // should be overrided
    }
}
