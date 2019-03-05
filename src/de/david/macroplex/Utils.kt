package de.david.macroplex

import de.david.javalib.MonitorController
import javafx.scene.paint.Color
import processing.core.PSurface

//Processing Utils:
fun PSurface.toCenter(width: Int, height: Int) {
    this.setLocation(MonitorController.getWidth()/2-width/2, MonitorController.getHeight()/2-height/2)
}

//JavaFx/TornadoFx Utils:
fun Color.toProcessingCustomizedColor(): de.david.macroplex.Color {
    return de.david.macroplex.Color((this.red*255).toFloat(), (this.green*255).toFloat(), (this.blue*255).toFloat(), (this.opacity*255).toFloat())
}