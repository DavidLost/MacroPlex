package de.david.macroplex

import de.david.javalib.MonitorController
import processing.core.PApplet
import processing.core.PVector

class Window : PApplet {

    val settingsController: SettingsView
    val points = ArrayList<Point>()
    val settings: Settings
    //val depth = 1000
    //lateinit var cam: PeasyCam

    constructor(settingsController: SettingsView, currentSettings: Settings) {
        this.settingsController = settingsController
        this.settings = currentSettings
    }

    /*fun updateSettings(settings: Settings) {
        this.pointAmount = settings.pointAmount.getValue()
        this.maxSpeed = settings.maxSpeed.getValue()
        this.minDist = settings.minDist.getValue()
        this.minSize = settings.minSize.getValue()
        this.maxSize = settings.maxSize.getValue()
        //frameResized(round(MonitorController.getWidth()/settings.frameSizeFactor), round(MonitorController.getHeight()/settings.frameSizeFactor))
        updatePointSettings()
    }*/

    fun updatePointSettings() {
        println("c")
        for (point in points) {
            point.vel = PVector(random(-settings.maxSpeed.getValue(), settings.maxSpeed.getValue()), random(-settings.maxSpeed.getValue(), settings.maxSpeed.getValue()))
            point.size = random(settings.minSize.getValue(), settings.maxSize.getValue())
        }
    }

    override fun settings() {
        size(round(MonitorController.getWidth()/1.5f), round(MonitorController.getHeight()/1.5f))
    }

    override fun setup() {

        for (i in 0 until pointAmount) {
            val r = random(25f)
            val g = random(185f)+60f
            val b = random(150f)+105f
            points.add(Point(
                random(width.toFloat()),
                random(height.toFloat()),
                //random(depth.toFloat()),
                PVector(random(-maxSpeed, maxSpeed), random(-maxSpeed, maxSpeed)),
                random(minSize, maxSize),
                color(r, g, b)
            ))
        }
        //cam = PeasyCam(this, 2500.0)
    }

    override fun draw() {

        if (settingsController.updateAvailable) {
            updateSettings(settingsController.currentSettings)
            settingsController.updateAvailable = false
        }

        background(40)
        for (point in points) {
            point.update()
            for (point2 in points) {
                val dist = dist(point.x, point.y, point2.x, point2.y)
                if (dist < minDist) {
                    stroke((red(point.color)+red(point2.color))/2,(green(point.color)+green(point2.color))/2, (blue(point.color)+blue(point2.color))/2, map(dist, minDist, 0f, 10f, 255f))
                    strokeWeight(map(dist, minDist, 0f, (point.size+point2.size)/20f, (point.size+point2.size)/6f))
                    line(point.x, point.y, point2.x, point2.y)
                }
            }
            point.draw()
        }
    }

    inner class Point(var x: Float, var y: Float, var vel: PVector, var size: Float, var color: Int) {

        fun update() {
            x += vel.x
            y += vel.y
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
}