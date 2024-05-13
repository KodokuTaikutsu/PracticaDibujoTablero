package com.example.practicadibujotablero.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.example.practicadibujotablero.R
import com.example.practicadibujotablero.ui.model.Tablero
import kotlin.math.abs
import kotlin.math.min

class TableroView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var model: Tablero = Tablero()
    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }


    private var emeraldImg: Bitmap
    private var onyxImg: Bitmap
    private var sapphireImg: Bitmap
    private var rubyImg: Bitmap
    private var topazImg: Bitmap
    private var diamondImg: Bitmap
    private var octahedronImg: Bitmap
    private var lightningImg: Bitmap
    private var fireImg: Bitmap

    private var selectedTile: Pair<Int, Int>? = null

    init {
        emeraldImg = BitmapFactory.decodeResource(context?.resources,
            R.drawable.emerald
        )
        onyxImg = BitmapFactory.decodeResource(context?.resources,
            R.drawable.onyx
        )
        sapphireImg = BitmapFactory.decodeResource(context?.resources,
            R.drawable.saphire
        )
        rubyImg = BitmapFactory.decodeResource(context?.resources,
            R.drawable.ruby
        )
        topazImg = BitmapFactory.decodeResource(context?.resources,
            R.drawable.topaz
        )
        diamondImg = BitmapFactory.decodeResource(context?.resources,
            R.drawable.diamond
        )
        octahedronImg = BitmapFactory.decodeResource(context?.resources,
            R.drawable.octahedron
        )
        lightningImg = BitmapFactory.decodeResource(context?.resources,
            R.drawable.lighting
        )
        fireImg = BitmapFactory.decodeResource(context?.resources,
            R.drawable.fire
        )

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val row = (event.y / height * model.filas).toInt()
                val col = (event.x / width * model.columnas).toInt()
                selectedTile = Pair(row, col)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                val newRow = (event.y / height * model.filas).toInt()
                val newCol = (event.x / width * model.columnas).toInt()
                if (selectedTile != null && newRow in 0 until model.filas && newCol in 0 until model.columnas) {
                    //can I even move this bullshit?
                    if (isAdjacent(selectedTile!!, Pair(newRow, newCol)) && validMove(selectedTile!!, Pair(newRow, newCol))) {
                        model.swapTiles(selectedTile!!, Pair(newRow, newCol))
                        val matchingTiles = model.detectMatchingLines() //check if you did a fucking match REEEEEEEEEEEEEEE

                        if (matchingTiles.isNotEmpty()) {
                            model.removeMatchingLines(matchingTiles)
                        }
                        if (!isGameOver()) {
                            selectedTile = null
                            invalidate()
                            // NOT DUN GOOFED YET KID
                        } else {
                            Toast.makeText(context, "Game Over", Toast.LENGTH_SHORT).show()
                            invalidate()
                            // YOU DUN GOOFED KID
                        }

                    }

                }
                invalidate()
            }
        }
        return true
    }



    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }




    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val board = model
        val size = measuredWidth.coerceAtMost(measuredHeight)
        val squareSize = size / board.columnas
        for (i in 0 until board.filas) {
            for (j in 0 until board.columnas) {

                when(board.matriz[i][j]){ //for my dreams and illusions, it will be only 8 steps to end it all and one more to go further than it should be allowed, it now, it is here, for my ego cant take anymore. don't let them see you don't let them see you don't let them see you don't let them see you don't let them see you don't let them see you don't let them see you don't let them see you don't let them see you don't let them see you don't let them see you don't let them see you don't let them see you don't let them see you don't let them see you
                    1 -> {
                        canvas.drawBitmap(
                            emeraldImg,
                            (j * squareSize).toFloat(),
                            (i * squareSize).toFloat(),
                            paint
                        )
                    }
                    2 -> {
                        canvas.drawBitmap(
                            rubyImg,
                            (j * squareSize).toFloat(),
                            (i * squareSize).toFloat(),
                            paint
                        )
                    }
                    3 -> {
                        canvas.drawBitmap(
                            sapphireImg,
                            (j * squareSize).toFloat(),
                            (i * squareSize).toFloat(),
                            paint
                        )
                    }
                    4 -> {
                        canvas.drawBitmap(
                            topazImg,
                            (j * squareSize).toFloat(),
                            (i * squareSize).toFloat(),
                            paint
                        )
                    }
                    5 -> {
                        canvas.drawBitmap(
                            diamondImg,
                            (j * squareSize).toFloat(),
                            (i * squareSize).toFloat(),
                            paint
                        )
                    }
                    6 -> {
                        canvas.drawBitmap(
                            onyxImg,
                            (j * squareSize).toFloat(),
                            (i * squareSize).toFloat(),
                            paint
                        )
                    }
                    7 -> {
                        canvas.drawBitmap(
                            fireImg,
                            (j * squareSize).toFloat(),
                            (i * squareSize).toFloat(),
                            paint
                        )
                    }
                    8 -> {
                        canvas.drawBitmap(
                            lightningImg,
                            (j * squareSize).toFloat(),
                            (i * squareSize).toFloat(),
                            paint
                        )
                    }
                    9 -> {
                        canvas.drawBitmap( //sabes to que hay mas alla de la muerte?
                            octahedronImg,
                            (j * squareSize).toFloat(),
                            (i * squareSize).toFloat(),
                            paint
                        )
                    }
                    else -> {
                        canvas.drawRect(
                            (j * squareSize).toFloat(),
                            (i * squareSize).toFloat(),
                            ((j + 1) * squareSize).toFloat(),
                            ((i + 1) * squareSize).toFloat(),
                            paint
                        )
                    }
                }
            }
        }
        //this is the light of my souls or an AT field as you Lilins would call, this is the boundary of my existence
        //as this denotes where my being ends and every other one begins. Shall one day we rejoice upon the
        //death of the body and the union of the souls, only the primordial conscience is to remain so both humanity
        //all suffering reach its end
        selectedTile?.let {
            val row = it.first
            val col = it.second
            val outlinePaint = Paint().apply {
                color = Color.YELLOW
                style = Paint.Style.STROKE
                strokeWidth = 8f
            }
            //make this entire shit a square
            canvas.drawRect(
                (col * squareSize).toFloat(),
                (row * squareSize).toFloat(),
                ((col + 1) * squareSize).toFloat(),
                ((row + 1) * squareSize).toFloat(),
                outlinePaint
            )
        }
    }

    private fun isAdjacent(tile1: Pair<Int, Int>, tile2: Pair<Int, Int>): Boolean { //this bullshit calculates if tiles are next to each other REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
        val rowDiff = abs(tile1.first - tile2.first)
        val colDiff = abs(tile1.second - tile2.second)
        return (rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1)
    }

    private fun validMove(tile1: Pair<Int, Int>, tile2: Pair<Int, Int>): Boolean { //can you do a fucking match 3????
        val temp = model.matriz[tile1.first][tile1.second]
        model.swapTiles(tile1, tile2)
        val matchingTiles = model.detectMatchingLines()
        model.swapTiles(tile1, tile2)
        return matchingTiles.isNotEmpty()
    }
    //Komm, süßer Tod, komm sel’ge Ruh!
    //Komm, führe mich in Friede
    //Weil ich der Welt bin müde
    private fun isGameOver(): Boolean {
        // can I keep moving? yes? no? god is dead anyway
        for (i in 0 until model.filas) {
            for (j in 0 until model.columnas) {
                if (model.matriz[i][j] != 0) {
                    // Check adjacent tiles
                    if ((i > 0 && model.matriz[i - 1][j] != 0 && validMove(Pair(i, j), Pair(i - 1, j))) ||
                        (i < model.filas - 1 && model.matriz[i + 1][j] != 0 && validMove(Pair(i, j), Pair(i + 1, j))) ||
                        (j > 0 && model.matriz[i][j - 1] != 0 && validMove(Pair(i, j), Pair(i, j - 1))) ||
                        (j < model.columnas - 1 && model.matriz[i][j + 1] != 0 && validMove(Pair(i, j), Pair(i, j + 1)))
                    ) {
                        return false // If at least one move is possible, return false
                    }
                }
            }
        }
        return true // If no possible moves are found, return true
    }

    fun reset() {
        model.resetMatriz()
        invalidate()
    }
    fun resetTest() {
        model.resetTestMatriz()
        invalidate()
    }


}