package de.david.macroplex.gui

import de.david.javalib.MonitorController
import de.david.macroplex.MaxSize
import de.david.macroplex.MinSize
import de.david.macroplex.Settings
import de.david.macroplex.toCenter
import processing.core.PApplet
import processing.core.PVector

class Window : PApplet {

    val settingsController: SettingsView
    val points = ArrayList<Point>()
    val settings: Settings

    constructor(settingsController: SettingsView, currentSettings: Settings) {
        this.settingsController = settingsController
        this.settings = currentSettings
    }

    override fun settings() {
        size(round(MonitorController.getWidth()/1.5f), round(MonitorController.getHeight()/1.5f))
    }

    override fun setup() {

        surface.toCenter(width, height)
        surface.setResizable(true)

        for (i in 0 until settings.pointAmount) {
            points.add(getNewPoint())
        }
    }

    override fun draw() {

        updateSettings()

        background(40)
        for (point in points) {
            point.update()
            for (point2 in points) {
                val dist = dist(point.x, point.y, point2.x, point2.y)
                if (dist < settings.connectionDistance) {
                    stroke((red(point.color)+red(point2.color))/2,(green(point.color)+green(point2.color))/2, (blue(point.color)+blue(point2.color))/2, map(dist, settings.connectionDistance, 0f, 10f, 255f))
                    strokeWeight(map(dist, settings.connectionDistance, 0f, (point.drawSize+point2.drawSize)/20f, (point.drawSize+point2.drawSize)/6f))
                    line(point.x, point.y, point2.x, point2.y)
                }
            }
            point.draw()
        }
    }

    inner class Point(var x: Float, var y: Float, var vel: PVector, var sizeFactor: Float, var color: Int) {

        var drawSize: Float = 0.0f

        init {
            updateSize()
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

        fun draw() {
            strokeWeight(drawSize)
            stroke(color)
            point(x, y)
        }

    }

    fun updateSettings() {

        when (settingsController.updateState) {
            SettingsView.NO_UPDATE -> return
            0 -> changePointAmount(settings.pointAmount)
            1 -> updatePointSize()
        }
        settingsController.updateState = SettingsView.NO_UPDATE
    }



    fun updatePointSize() {
        for (point in points) {
            point.updateSize()
        }
    }

    fun changePointAmount(newAmount: Int) {
        if (newAmount == points.size) return
        else if (newAmount > points.size) {
            while (points.size < newAmount) {
                points.add(getNewPoint())
            }
        }
        else {
            while (points.size > newAmount) {
                points.removeAt(0)
            }
        }
    }

    fun getNewPoint(): Point {
        val r = random(25f)
        val g = random(185f)+60f
        val b = random(150f)+105f
        var xVel = random(0.1f, 1f)
        if (random(2f) > 1) xVel *= -1
        var yVel = random(0.1f, 1f)
        if (random(2f) > 1) yVel *= -1
        return Point(
            random(width.toFloat()),
            random(height.toFloat()),
            //random(depth.toFloat()),
            PVector(xVel, yVel),
            (random(
                MinSize.MIN,
                MaxSize.MAX
            )- MinSize.MIN)/(MaxSize.MAX - MinSize.MIN),
            color(r, g, b)
        )
    }
}