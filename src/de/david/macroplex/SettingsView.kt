package de.david.macroplex

import javafx.geometry.Pos
import javafx.scene.control.Label
import processing.core.PApplet
import tornadofx.*

class SettingsView : View(MyApp.APP_NAME+" Settings") {

    companion object {
        val NO_UPDATE = -1
    }

    val currentSettings = Settings(PointAmount.DEFAULT, SpeedFactor.DEFAULT, ConnectionDistance.DEFAULT, MinSize.DEFAULT, MaxSize.DEFAULT, 1.5f)
    var updateState = NO_UPDATE

    lateinit var pointAmountLabel: Label
    lateinit var speedFactorLabel: Label
    lateinit var connectionDistanceLabel: Label
    lateinit var minSizeLabel: Label
    lateinit var maxSizeLabel: Label

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
                    pointAmountLabel.text = currentSettings.pointAmount.toString()
                    updateState = 0
                }
            }
            label(PointAmount.DEFAULT.toString()) {
                pointAmountLabel = this
            }
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
                    updateLabelValue(speedFactorLabel, currentSettings.speedFactor)
                }
            }
            label(SpeedFactor.DEFAULT.toString()) {
                speedFactorLabel = this
            }
        }

        hbox {
            alignment = Pos.CENTER
            label("Connecting Distance")
            slider(ConnectionDistance.MIN, ConnectionDistance.MAX) {
                value = ConnectionDistance.DEFAULT.toDouble()
                majorTickUnit = max
                prefWidth = 300.0
                valueProperty().onChange {
                    currentSettings.connectionDistance = it.toFloat()
                    updateLabelValue(connectionDistanceLabel, currentSettings.connectionDistance)
                }
            }
            label(ConnectionDistance.DEFAULT.toString()) {
                connectionDistanceLabel = this
            }
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
                    updateLabelValue(minSizeLabel, currentSettings.minSize)
                    updateState = 1
                }
            }

            label(MinSize.DEFAULT.toString()) {
                minSizeLabel = this
            }
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
                    updateLabelValue(maxSizeLabel, currentSettings.maxSize)
                    updateState = 1
                }
            }
            label(MaxSize.DEFAULT.toString()) {
                maxSizeLabel = this
            }
        }
    }

    fun updateLabelValue(label: Label, value: Float) {
        var string = String.format("%.2f", value).replace(',', '.')
        if (string.endsWith('0')) string = string.substring(0, string.length-1)
        label.text = string
    }

}
