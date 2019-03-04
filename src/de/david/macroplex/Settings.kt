package de.david.macroplex

class Settings(var pointAmount: PointAmount, var maxSpeed: MaxSpeed, var minDist: MinDist, var minSize: MinSize, var maxSize: MaxSize, var frameSizeFactor: Float) {

    /*companion object {
        val DEFAULT_POINT_AMOUNT = 36
        val DEFAULT_MAX_SPEED = 1.8f
        val DEFAULT_MIN_DIST = 210f
        val DEFAULT_MIN_SIZE = 3f
        val DEFAULT_MAX_SIZE = 12f
        val DEFAULT_FRAME_SIZE_FACTOR = 1.5f
    }*/

    /*var pointAmount by property<Int>(36)
    fun pointAmountProperty() = getProperty(Settings::pointAmount)

    var maxSpeed by property<Float>(1.8f)
    fun maxSpeedProperty() = getProperty(Settings::maxSpeed)

    var minDist by property<Float>(210f)
    fun minDistProperty() = getProperty(Settings::minDist)

    var minSize by property<Float>(3f)
    fun minSizeProperty() = getProperty(Settings::minSize)

    var maxSize by property<Float>(12f)
    fun maxSizeProperty() = getProperty(Settings::maxSize)

    var frameSizeFactor by property<Float>(1.5f)
    fun frameSizeFactorProperty() = getProperty(Settings::frameSizeFactor)*/
}

class PointAmount {
    var value = DEFAULT
    companion object {
        val DEFAULT = 36
        val MIN = 1
        val MAX = 200
    }
}

class MaxSpeed {
    var value = DEFAULT
    companion object {
        val DEFAULT = 1.8f
        val MIN = 0.1
        val MAX = 10
    }
}

class MinDist {
    var value = DEFAULT
    companion object {
        val DEFAULT = 210f
        val MIN = 1
        val MAX = 1000
    }
}

class MinSize {
    var value = DEFAULT
    companion object {
        val DEFAULT = 3f
        val MIN = 1
        val MAX = 1000
    }
}

class MaxSize {
    var value = DEFAULT
    companion object {
        val DEFAULT = 12f
        val MIN = 1
        val MAX = 1000
    }
}