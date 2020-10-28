package com.anandbose.demo.swipedismiss

import android.content.Context
import android.graphics.*
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeCallback(context: Context, val adapter: RecipeNameAdapter) :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    companion object {
        const val TAG = "SwipeCallback"
    }

    var deleteButtonText: String? = "Delete"
    var deleteButtonTypeface: Typeface = Typeface.DEFAULT
    var deleteButtonTextSize = 14f

    private val dp = context.resources.displayMetrics.density
    private val redRectPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textRect = Rect()

    init {
        redRectPaint.color = Color.RED
        textPaint.color = Color.WHITE
        onDeleteButtonUpdated()
    }

    override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(
            viewHolder: RecyclerView.ViewHolder,
            direction: Int
    ) {
        val position = viewHolder.adapterPosition
        adapter.recipes?.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        drawRedRect(c, dX, viewHolder.itemView)
        drawText(c, dX, viewHolder.itemView)
    }

    private fun drawRedRect(canvas: Canvas, dX: Float, view: View) {
        val right = canvas.width.toFloat()
        val left = right + dX
        canvas.drawRect(
                left,
                view.top.toFloat(),
                right,
                view.bottom.toFloat(),
                redRectPaint
        )
    }

    private fun drawText(canvas: Canvas, dX: Float, view: View) {
        val text = deleteButtonText
        if (text != null) {
            val cw = canvas.width.toFloat()
            val left = cw + dX
            val top = view.top.toFloat()
            val bottom = view.bottom.toFloat()
            val ty = (bottom - top) / 2f + textRect.height().toFloat() / 2f + top
            if (-dX < (textRect.width().toFloat() + 2f * 16f * dp)) {
                val tx = left + 16f * dp
                canvas.drawText(text, tx, ty, textPaint)
            } else {
                val tx = cw - textRect.width().toFloat() - 16f * dp
                canvas.drawText(text, tx, ty, textPaint)
            }
        }
    }

    private fun onDeleteButtonUpdated() {
        val text = this.deleteButtonText
        if (text != null) {
            textPaint.color = Color.WHITE
            textPaint.typeface = deleteButtonTypeface
            textPaint.textSize = deleteButtonTextSize * dp
            textPaint.getTextBounds(text, 0, text.length, textRect)
        }
    }
}