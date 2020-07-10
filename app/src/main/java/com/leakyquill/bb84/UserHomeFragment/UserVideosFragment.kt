package com.leakyquill.bb84.UserHomeFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.leakyquill.bb84.Adapter.CardStackAdapter
import com.leakyquill.bb84.Adapter.CardStackVideoAdapter
import com.leakyquill.bb84.Callback.SpotDiffCallback
import com.leakyquill.bb84.Model.Spot

import com.leakyquill.bb84.R
import com.yuyakaido.android.cardstackview.*

/**
 * A simple [Fragment] subclass.
 */
class UserVideosFragment : Fragment(), CardStackListener{

    private lateinit var cardStackView: CardStackView
    private lateinit var manager : CardStackLayoutManager
    private val adapter : CardStackVideoAdapter by lazy { CardStackVideoAdapter(createSpots()) }
    private lateinit var rewind : FloatingActionButton
    private var counter = 0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_user_videos, container, false)

        rewind = view.findViewById(R.id.rewind)

        cardStackView = view.findViewById(R.id.card_stack_view_videos)
        manager = CardStackLayoutManager(context,this)


        setupCardStackView()

        rewind.setOnClickListener {
            cardStackView.rewind()
        }

        return view
    }

    override fun onCardDragging(direction: Direction, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction) {
        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")

        if (manager.topPosition == adapter.itemCount - 1) {
            paginate()
            Log.i("Video set is Completed", "-----yes it is")
        }
    }

    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")

    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }

    override fun onCardAppeared(view: View, position: Int) {

    }

    override fun onCardDisappeared(view: View, position: Int) {

    }

    private fun setupCardStackView() {
        initialize()
    }

    private fun initialize() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(false)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    private fun paginate() {
        val old = adapter.getSpots()
        val new = old.plus(createSpots())
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }



    private fun createSpots(): List<Spot> {
        val spots = ArrayList<Spot>()


        spots.add(Spot(name = "Big fat Bunny", city = "Jungle", url = "", postText = "", video = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))

        spots.add(Spot(name = "The open movie project", city = "Kyoto", url = "", postText = "", video = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"))

        return spots
    }

}