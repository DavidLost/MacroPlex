package de.david.macroplex.gui

import de.david.javalib.MonitorController
import de.david.macroplex.*
import javafx.beans.property.SimpleIntegerProperty
import processing.core.PApplet
import processing.core.PVector

class Window : PApplet {

    val settings: Settings
    val updateState: SimpleIntegerProperty
    val points = ArrayList<Point>()

    constructor(currentSettings: Settings, updateState: SimpleIntegerProperty) {
        this.settings = currentSettings
        this.updateState = updateState
    }

    override fun settings() {
        size(round(MonitorController.getWidth()/1.5f), round(MonitorController.getHeight()/1.5f))
    }

    override fun setup() {

        surface.setTitle(MyApp.APP_NAME)
        surface.centerOnScreen(width, height)
        surface.setResizable(true)

        for (i in 0 until settings.pointAmount) {
            points.add(getNewPoint())
        }
    }

    override fun draw() {

        //updating
        updateSettings()
        for (point in points) {
            point.update()
        }

        //drawing
        background(settings.backgroundColor.red, settings.backgroundColor.green, settings.backgroundColor.blue, settings.backgroundColor.opacity)
        drawLinesBetweenPoints()
        for (point in points) {
            point.draw()
        }
    }

    fun drawLinesBetweenPoints() {
        for (point1 in points) {
            for (point2 in points) {
                val dist = dist(point1.x, point1.y, point2.x, point2.y)
                if (dist <= settings.connectionDistance) {
                    val r: Float; val g: Float; val b: Float; val o: Float
                    if (settings.calcConnectionColor) {
                        r = (point1.color.red+point2.color.red)/2f
                        g = (point1.color.green+point2.color.green)/2f
                        b = (point1.color.blue+point2.color.blue)/2f
                        o = map(dist, settings.connectionDistance, 0f, 0f, (point1.color.opacity+point2.color.opacity)/2)
                    }
                    else {
                        r = settings.connectionColor.red
                        g = settings.connectionColor.green
                        b = settings.connectionColor.blue
                        o = map(dist, settings.connectionDistance, 0f, 0f, settings.connectionColor.opacity)
                    }
                    stroke(r, g, b, o)
                    strokeWeight(map(dist, settings.connectionDistance, 0f, (point1.drawSize+point2.drawSize)/20f, (point1.drawSize+point2.drawSize)/6f))
                    line(point1.x, point1.y, point2.x, point2.y)
                }
            }
        }
    }

    inner class Point(var x: Float, var y: Float, var vel: PVector, var sizeFactor: Float) {

        var drawSize: Float = 0.0f
        val color: Color = Color(0f, 0f, 0f, 0f)

        init {
            updateSize()
            updateColor()
        }

        fun update() {
            x += vel.x*settings.speedFactor
            y += vel.y*settings.speedFactor
            //z += vel.z
            if (x >= width) x -= width
            else if (x < 0) x += width
            if (y >= height) y -= height
            else if (y < 0) y += height
            //if (z >= depth) z -= depth
            //else if (z < 0) z += depth
        }

        fun updateSize() {
            drawSize = (sizeFactor*(settings.maxSize-settings.minSize))+settings.minSize
        }

        fun updateColor() {
            color.red = if (settings.color2.red >= settings.color1.red) random(settings.color1.red, settings.color2.red)
                        else random(settings.color2.red, settings.color1.red)
            color.green = if (settings.color2.green >= settings.color1.green) random(settings.color1.green, settings.color2.green)
                          else random(settings.color2.green, settings.color1.green)
            color.blue = if (settings.color2.blue >= settings.color1.blue) random(settings.color1.blue, settings.color2.blue)
                         else random(settings.color2.blue, settings.color1.blue)
            color.opacity = if (settings.color2.opacity >= settings.color1.opacity) random(settings.color1.opacity, settings.color2.opacity)
                            else random(settings.color2.opacity, settings.color1.opacity)
        }

        fun draw() {
            strokeWeight(drawSize)
            stroke(color.red, color.green, color.blue, color.opacity)
            point(x, y)
        }

    }

    fun updateSettings() {

        when (updateState.value) {
            SettingsView.NO_UPDATE -> return
            SettingsView.POINT_AMOUNT_UPDATE -> changePointAmount()
            SettingsView.POINT_SIZE_UPDATE -> updatePointSize()
            SettingsView.POINT_COLOR_UPDATE -> updatePointColor()
        }
        println("update")
        updateState.value = SettingsView.NO_UPDATE
    }

    fun updatePointColor() {
        for (point in points) {
            point.updateColor()
        }
    }

    fun updatePointSize() {
        for (point in points) {
            point.updateSize()
        }
    }

    fun changePointAmount() {
        if (settings.pointAmount == points.size) return
        else if (settings.pointAmount > points.size) {
            while (points.size < settings.pointAmount) {
                points.add(getNewPoint())
            }
        }
        else {
            while (points.size > settings.pointAmount) {
                points.removeAt(0)
            }
        }
    }

    fun getNewPoint(): Point {
        var xVel = random(0.1f, 1f)
        if (random(2f) > 1) xVel *= -1
        var yVel = random(0.1f, 1f)
        if (random(2f) > 1) yVel *= -1
        return Point(
            random(width.toFloat()),
            random(height.toFloat()),
            PVector(xVel, yVel),
            random(0f, 1f)
        )
    }
}