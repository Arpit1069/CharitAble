package com.example.charitable.models


import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.charitable.R
import com.google.firebase.database.ValueEventListener
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


abstract class SwipeGesture(context: Context) : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN
        or ItemTouchHelper.START or ItemTouchHelper.END ,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    val deletecolor = ContextCompat.getColor(context, R.color.deletecolor)
    val archivecolor = ContextCompat.getColor(context, R.color.archivecolor)
    val deleteicon = R.drawable.ic_home
    val archiveicon = R.drawable.ic_home


    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        RecyclerViewSwipeDecorator.Builder(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
            .addSwipeLeftBackgroundColor(deletecolor)
            .addSwipeLeftActionIcon(deleteicon)
            .addSwipeRightBackgroundColor(archivecolor)
            .addSwipeRightActionIcon(archiveicon)
            .create()
            .decorate()


        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }


}