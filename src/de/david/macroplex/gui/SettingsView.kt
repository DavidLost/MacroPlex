package de.david.macroplex.gui

import de.david.macroplex.*
import javafx.geometry.Pos
import javafx.scene.control.Label
import processing.core.PApplet
import tornadofx.*

class SettingsView : View(MyApp.APP_NAME +" Settings") {

    companion object {
        val NO_UPDATE = -1
        val POINT_AMOUNT_UPDATE = 0
        val POINT_SIZE_UPDATE = 1
        val POINT_COLOR_UPDATE = 2
    }

    val currentSettings = Settings(
        PointAmount.DEFAULT,
        SpeedFactor.DEFAULT,
        ConnectionDistance.DEFAULT,
        MinSize.DEFAULT,
        MaxSize.DEFAULT,
        Color1.DEFAULT,
        Color2.DEFAULT
    )
    var updateState = NO_UPDATE

    override fun onDock() {

        startSimulation()
        primaryStage.width = 630.0
        primaryStage.height = 320.0
        primaryStage.isResizable = false
        primaryStage.centerOnScreen()
        primaryStage.toFront()
    }

    fun startSimulation() {
        val args = arrayOf(
            "--location=0,0",
            MyApp.APP_NAME
        )
        PApplet.runSketch(args, Window(this, currentSettings))
    }

    override val root = vbox(20) {

        alignment = Pos.CENTER

        hbox {
            alignment = Pos.CENTER
            label("Point Amount")
            slider(PointAmount.MIN, PointAmount.MAX) {
                value = PointAmount.DEFAULT.toDouble()
                majorTickUnit = max
                prefWidth = 300.0
                //showTickLabelsProperty().setValue(true)
                valueProperty().onChange {
                    currentSettings.pointAmount = PApplet.round(it.toFloat())
                    (this@hbox.children.get(2) as Label).text = currentSettings.pointAmount.toString()
                    updateState = POINT_AMOUNT_UPDATE
                }
            }
            label(PointAmount.DEFAULT.toString())
        }

        hbox {
            alignment = Pos.CENTER
            label("SpeedFactor")
            slider(SpeedFactor.MIN, SpeedFactor.MAX) {
                value = SpeedFactor.DEFAULT.toDouble()
                majorTickUnit = max
                prefWidth = 300.0
                //showTickLabelsProperty().setValue(true)
                valueProperty().onChange {
                    currentSettings.speedFactor = it.toFloat()
                    updateLabelValue(this@hbox.children.get(2) as Label, currentSettings.speedFactor)
                }
            }
            label(SpeedFactor.DEFAULT.toString())
        }

        hbox {
            alignment = Pos.CENTER
            label("Connecting Distance")
            slider(
                ConnectionDistance.MIN,
                ConnectionDistance.MAX
            ) {
                value = ConnectionDistance.DEFAULT.toDouble()
                majorTickUnit = max
                prefWidth = 300.0
                valueProperty().onChange {
                    currentSettings.connectionDistance = it.toFloat()
                    updateLabelValue(this@hbox.children.get(2) as Label, currentSettings.connectionDistance)
                }
            }
            label(ConnectionDistance.DEFAULT.toString())
        }

        hbox {
            alignment = Pos.CENTER
            label("Min. Size")
            slider(MinSize.MIN, MinSize.MAX) {
                value = MinSize.DEFAULT.toDouble()
                majorTickUnit = max
                prefWidth = 300.0
                //showTickLabelsProperty().setValue(true)
                valueProperty().onChange {
                    currentSettings.minSize = it.toFloat()
                    updateLabelValue(this@hbox.children.get(2) as Label, currentSettings.minSize)
                    updateState = POINT_SIZE_UPDATE
                }
            }

            label(MinSize.DEFAULT.toString())
        }

        hbox {
            alignment = Pos.CENTER
            label("Max. Size")
            slider(MaxSize.MIN, MaxSize.MAX) {
                value = MaxSize.DEFAULT.toDouble()
                majorTickUnit = max
                prefWidth = 300.0
                //showTickLabelsProperty().setValue(true)
                valueProperty().onChange {
                    currentSettings.maxSize = it.toFloat()
                    updateLabelValue(this@hbox.children.get(2) as Label, currentSettings.maxSize)
                    updateState = POINT_SIZE_UPDATE
                }
            }
            label(MaxSize.DEFAULT.toString())
        }

        hbox(10) {
            alignment = Pos.CENTER
            label("Point Color 1:")
            colorpicker(Color1.DEFAULT.toJavaFxColor()) {
                setOnAction {
                    currentSettings.color1 = value.toProcessingCustomizedColor()
                    updateState = POINT_COLOR_UPDATE
                }
            }
            label("2:")
            colorpicker(Color2.DEFAULT.toJavaFxColor()) {
                setOnAction {
                    currentSettings.color2 = value.toProcessingCustomizedColor()
                    updateState = POINT_COLOR_UPDATE
                }
            }
        }

        hbox(10) {
            alignment = Pos.CENTER
            label("Connection Color")
            colorpicker(Color1.DEFAULT.toJavaFxColor()) {
                setOnAction {
                    currentSettings.color1 = value.toProcessingCustomizedColor()
                }
            }
        }
    }

    fun updateLabelValue(label: Label, value: Float) {
        var string = String.format("%.2f", value).replace(',', '.')
        if (string.endsWith('0')) string = string.substring(0, string.length-1)
        label.text = string
    }

}
