package de.david.macroplex

import de.david.javalib.MonitorController
import processing.core.PSurface

fun PSurface.toCenter(width: Int, height: Int) {
    this.setLocation(MonitorController.getWidth()/2-width/2, MonitorController.getHeight()/2-height/2)
}