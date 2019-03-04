package de.david.macroplex

class Settings(var pointAmount: Int, var speedFactor: Float, var connectionDistance: Float, var minSize: Float, var maxSize: Float, var frameSizeFactor: Float)

abstract class PointAmount {
    companion object {
        val DEFAULT = 1
        val MIN = 1
        val MAX = 200
    }
}

abstract class SpeedFactor {
    companion object {
        val DEFAULT = 1.8f
        val MIN = 0f
        val MAX = 10f
    }
}

abstract class ConnectionDistance {
    companion object {
        val DEFAULT = 210f
        val MIN = 1f
        val MAX = 1000f
    }
}

class MinSize {
    companion object {
        val DEFAULT = 3f
        val MIN = 1f
        val MAX = 32f
    }
}

class MaxSize {
    companion object {
        val DEFAULT = 12f
        val MIN = 1f
        val MAX = 32f
    }
}