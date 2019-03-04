package de.david.macroplex

import de.david.javalib.MonitorController
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

        for (i in 0 until settings.pointAmount.value) {
            addNewPoint()
        }
    }

    override fun draw() {

        if (settingsController.updateState != SettingsView.NO_UPDATE) {
            updatePointSettings(settingsController.updateState)
            settingsController.updateState = SettingsView.NO_UPDATE
        }

        background(40)
        for (point in points) {
            point.update()
            for (point2 in points) {
                val dist = dist(point.x, point.y, point2.x, point2.y)
                if (dist < settings.minDist.value) {
                    stroke((red(point.color)+red(point2.color))/2,(green(point.color)+green(point2.color))/2, (blue(point.color)+blue(point2.color))/2, map(dist, settings.minDist.value, 0f, 10f, 255f))
                    strokeWeight(map(dist, settings.minDist.value, 0f, (point.size+point2.size)/20f, (point.size+point2.size)/6f))
                    line(point.x, point.y, point2.x, point2.y)
                }
            }
            point.draw()
        }
    }

    inner class Point(var x: Float, var y: Float, var vel: PVector, var size: Float, var color: Int) {

        fun update() {
            x += vel.x*settings.speed.value
            y += vel.y*settings.speed.value
            //z += vel.z
            if (x >= width) x -= width
            else if (x < 0) x += width
            if (y >= height) y -= height
            else if (y < 0) y += height
            //if (z >= depth) z -= depth
            //else if (z < 0) z += depth
        }

        fun draw() {
            strokeWeight(size)
            stroke(color)
            point(x, y)
        }

    }

    fun updatePointSettings(index: Int) {
        when (index) {
            0 -> changePointAmount(settings.pointAmount.value)
            1 -> settings.speed.value = settingsController.currentSettings.speed.value
        }
    }

    fun changePointAmount(newAmount: Int) {
        if (newAmount == points.size) return
        else if (newAmount > points.size) {
            while (points.size < newAmount) {
                addNewPoint()
            }
        }
        else {
            while (points.size > newAmount) {
                points.removeAt(0)
            }
        }
    }

    fun addNewPoint() {
        val r = random(25f)
        val g = random(185f)+60f
        val b = random(150f)+105f
        var xVel = random(0.1f, 1f)
        if (random(2f) > 1) xVel *= -1
        var yVel = random(0.1f, 1f)
        if (random(2f) > 1) yVel *= -1
        points.add(Point(
            random(width.toFloat()),
            random(height.toFloat()),
            //random(depth.toFloat()),
            PVector(xVel, yVel),
            random(settings.minSize.value, settings.maxSize.value),
            color(r, g, b)
        ))
    }
}