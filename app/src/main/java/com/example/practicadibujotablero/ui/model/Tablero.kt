package com.example.practicadibujotablero.ui.model

class Tablero {
    var filas = 8
    var columnas = 8

    //and so the awakening of the old ones calls upon the beginning of the end
    //AWAKEN AWAKEN OH DEMON SULTAN AZATHOT AND SHALL THE DREAM OF REALITY CRUMBLE UPON NOTHINGNESS

    var matriz: Array<Array<Int>> = arrayOf(
        arrayOf(3, 6, 6, 4, 6, 6, 3, 4),
        arrayOf(1, 4, 2, 6, 3, 5, 4, 2),
        arrayOf(2, 5, 2, 4, 4, 2, 6, 1),
        arrayOf(4, 6, 5, 2, 5, 6, 5, 4),
        arrayOf(1, 4, 6, 1, 1, 2, 5, 3),
        arrayOf(3, 6, 5, 1, 2, 1, 2, 6),
        arrayOf(1, 6, 3, 4, 1, 1, 2, 6),
        arrayOf(4, 2, 3, 3, 1, 4, 6, 5)
    )

//    var matriz: Array<Array<Int>>
//
//    init {
//        do {
//            matriz = Array(filas) { Array(columnas) { (1..6).random() } }
//        } while (initialMatchesExist())
//    }

    //Uhhhhh, I'm not like other girls? I cut myself with vertical lines
    //ugh, get a hint
    private fun initialMatchesExist(): Boolean {
        for (i in 0 until filas) {
            for (j in 0 until columnas) {
                val currentValue = matriz[i][j]
                // Check horizontally
                if (j >= 2 && matriz[i][j - 1] == currentValue && matriz[i][j - 2] == currentValue) {
                    return true
                }
                // Check vertically
                if (i >= 2 && matriz[i - 1][j] == currentValue && matriz[i - 2][j] == currentValue) {
                    return true
                }
            }
        }
        return false
    }


    fun swapTiles(tile1: Pair<Int, Int>, tile2: Pair<Int, Int>) {
        val temp = matriz[tile1.first][tile1.second]
        matriz[tile1.first][tile1.second] = matriz[tile2.first][tile2.second]
        matriz[tile2.first][tile2.second] = temp


        if (temp == 7) { // did i move a fucking fire?
            // burn shit
            removeSurroundingTiles(tile1)
        } else if (matriz[tile2.first][tile2.second] == 7) { // did i move a fucking fire in a funny way?
            // burn more shit
            removeSurroundingTiles(tile2)
        }
        if (temp == 8) { // I wanna bathe with the toaster
            // zap shit
            removeCross(tile1)
        } else if (matriz[tile2.first][tile2.second] == 7) { // I wanna shower with the toaster
            // zap more shit
            removeCross(tile2)
        }
        if (temp == 9) { // HAVE YOUR MORTAL TOUCHED THE OCTAHEDRON OF POWER?
            // LET HUMAN INSTRUMENTALIZATION BEGIN
            allHailTheOctahedron(tile1)
        } else if (matriz[tile2.first][tile2.second] == 9) { // HAVE YOUR MORTAL MADE MATTER TOUCH THE OCTAHEDRON OF POWER?
            // LET HUMAN INSTRUMENTALIZATION BEGIN
            allHailTheOctahedron(tile2)
        }
    }

    private fun allHailTheOctahedron(tile: Pair<Int, Int>) {
        val (row, col) = tile
        val deathOfAllLife = matriz[row][col]
        //GOD IS DEAD FOR THE OCTAHEDRON IN HERE
        for (i in 0 until filas){
            for (j in 0 until columnas){
                if(matriz[i][j] == deathOfAllLife || matriz[i][j] == 9 ){ // THE OCTAHEDRON MUST PERISH WITH THE FATE OF THIS REALITY
                    matriz[i][j] = 0
                }

            }
        }

        shiftTilesDown()
    }

    private fun removeCross(tile: Pair<Int, Int>) {
        val (row, col) = tile

        //zap vertically
        for (i in 0 until filas){
            matriz[row][i] = 0
        }
        //zap horizontally
        for (i in 0 until columnas){
            matriz[i][col] = 0
        }
        shiftTilesDown()
    }

    private fun removeSurroundingTiles(tile: Pair<Int, Int>) {
        val (row, col) = tile
        for (i in (row - 1)..(row + 1)) {
            for (j in (col - 1)..(col + 1)) {
                if (i in 0 until filas && j in 0 until columnas) {
                    matriz[i][j] = 0
                }
            }
        }
        shiftTilesDown()
    }

fun detectMatchingLines(): List<Pair<Int, Int>> {
    val matchingTiles = mutableListOf<Pair<Int, Int>>()

    // Check for horizontal lines
    for (i in 0 until filas) {
        var count = 1
        var startCol = -1
        var prevValue = matriz[i][0]
        for (j in 1 until columnas) {
            val currentValue = matriz[i][j]
            if (currentValue == prevValue && currentValue != 0) {
                if (count == 1) startCol = j - 1 // Marks the start of the streak
                count++
                if (count >= 3 && j == columnas - 1) {
                    for (k in startCol..j) {
                        matchingTiles.add(Pair(i, k))
                    }
                }
            } else {
                if (count >= 3) {
                    for (k in startCol..<j) {
                        matchingTiles.add(Pair(i, k))
                    }
                }
                count = 1
                startCol = -1
                prevValue = currentValue
            }
        }
    }

    // Check for vertical lines
    for (j in 0 until columnas) {
        var count = 1
        var startRow = -1
        var prevValue = matriz[0][j]
        for (i in 1 until filas) {
            val currentValue = matriz[i][j]
            if (currentValue == prevValue && currentValue != 0) {
                if (count == 1) startRow = i - 1 // Marks the start of the streak
                count++
                if (count >= 3 && i == filas - 1) {
                    for (k in startRow..i) {
                        matchingTiles.add(Pair(k, j))
                    }
                }
            } else {
                if (count >= 3) {
                    for (k in startRow..<i) {
                        matchingTiles.add(Pair(k, j))
                    }
                }
                count = 1
                startRow = -1
                prevValue = currentValue
            }
        }
    }

    return matchingTiles
}

    fun removeMatchingLines(matchingTiles: List<Pair<Int, Int>>) {
        matchingTiles.forEach { (row, col) ->
            matriz[row][col] = 0
        }
        if (matchingTiles.size == 4){
            matriz[matchingTiles[matchingTiles.size -1].first][matchingTiles[matchingTiles.size -1].second] = 7
        }
        if (matchingTiles.size == 5){
            matriz[matchingTiles[matchingTiles.size -1].first][matchingTiles[matchingTiles.size -1].second] = 9
        }
        if (matchingTiles.size > 5){
            matriz[matchingTiles[matchingTiles.size -1].first][matchingTiles[matchingTiles.size -1].second] = 8
        }
        shiftTilesDown() // Shift tiles down after removing matching lines
    }

    fun shiftTilesDown() {
        var matchesFound: Boolean
        do {
            matchesFound = false
            for (j in 0 until columnas) {
                var emptyCount = 0
                for (i in filas - 1 downTo 0) {
                    if (matriz[i][j] == 0) {
                        emptyCount++
                    } else if (emptyCount > 0) {
                        matriz[i + emptyCount][j] = matriz[i][j]
                        matriz[i][j] = 0
                    }
                }
                // Fill the FUCKING EMPTY TILES AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                for (i in 0 until emptyCount) {
                    matriz[i][j] = (1..6).random()
                }
            }
            val matchingTiles = detectMatchingLines()
            if (matchingTiles.isNotEmpty()) {
                removeMatchingLines(matchingTiles)
                matchesFound = true
            }
        } while (matchesFound)
    }

    //so unique, yet so painfully as worthless as the rest
    fun resetMatriz() {
        do {
            matriz = Array(filas) { Array(columnas) { (1..6).random() } }
        } while (initialMatchesExist())

    }

    //may the sorrow of the souls burn upon your body for eternity
    fun resetTestMatriz() {
        matriz = arrayOf(
        arrayOf(3, 6, 6, 4, 6, 6, 3, 4),
        arrayOf(1, 4, 2, 6, 3, 5, 4, 2),
        arrayOf(2, 5, 2, 4, 4, 2, 6, 1),
        arrayOf(4, 6, 5, 2, 5, 6, 5, 4),
        arrayOf(1, 4, 6, 1, 1, 2, 5, 3),
        arrayOf(3, 6, 5, 1, 2, 1, 2, 6),
        arrayOf(1, 6, 3, 4, 1, 1, 2, 6),
        arrayOf(4, 2, 3, 3, 1, 4, 6, 5)
        )
    }


}