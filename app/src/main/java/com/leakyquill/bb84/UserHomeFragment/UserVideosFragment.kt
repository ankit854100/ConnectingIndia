package com.leakyquill.bb84.UserHomeFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.leakyquill.bb84.Adapter.CardStackVideoAdapter
import com.leakyquill.bb84.Model.Spot
import com.leakyquill.bb84.R
import com.yuyakaido.android.cardstackview.*


/**
 * A simple [Fragment] subclass.
 */
class UserVideosFragment : Fragment(), CardStackListener{

    private lateinit var cardStackView: CardStackView
    private var manager : CardStackLayoutManager? = null
    private var adapter : CardStackVideoAdapter? = null
    private lateinit var rewind : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_user_videos, container, false)

        rewind = view.findViewById(R.id.rewind)

        cardStackView = view.findViewById(R.id.card_stack_view_videos)

//        adapter = context?.let { CardStackVideoAdapter(it, createSpots()) }!!
//        manager = CardStackLayoutManager(context,this)
//
//
//        setupCardStackView()

        rewind.setOnClickListener {
            cardStackView.rewind()
        }

        return view
    }

    override fun onCardDragging(direction: Direction, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction) {
        Log.d("CardStackView", "onCardSwiped: p = ${manager?.topPosition}, d = $direction")

        if (manager?.topPosition == adapter?.itemCount) {
//            paginate()

            Log.i("Video set is Completed", "-----yes it is")
        }
    }

    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager?.topPosition}")

    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager?.topPosition}")
    }

    override fun onCardAppeared(view: View, position: Int) {
        val playerView : PlayerView = view.findViewById(R.id.simpleExoPlayerView)
        playerView.visibility = View.VISIBLE
        adapter?.viewHolder?.playWhenReady = true
    }

    override fun onCardDisappeared(view: View, position: Int) {
        val playerView : PlayerView = view.findViewById(R.id.simpleExoPlayerView)
        playerView.visibility = View.GONE
        adapter?.viewHolder?.releasePlayer()

    }

    private fun setupCardStackView() {
        initialize()
    }

    private fun initialize() {
        manager?.setStackFrom(StackFrom.None)
        manager?.setVisibleCount(1)
        manager?.setTranslationInterval(8.0f)
        manager?.setScaleInterval(0.95f)
        manager?.setSwipeThreshold(0.3f)
        manager?.setMaxDegree(20.0f)
        manager?.setDirections(Direction.HORIZONTAL)
        manager?.setCanScrollHorizontal(true)
        manager?.setCanScrollVertical(false)
        manager?.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager?.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

//    private fun paginate() {
//        val old = adapter.getSpots()
//        val new = old.plus(createSpots())
//        val callback = SpotDiffCallback(old, new)
//        val result = DiffUtil.calculateDiff(callback)
//        adapter.setSpots(new)
//        result.dispatchUpdatesTo(adapter)
//    }



    private fun createSpots(): List<Spot> {
        val spots = ArrayList<Spot>()


        spots.add(Spot(name = "Big fat Bunny", city = "Jungle", url = "", postText = "", video = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))

        spots.add(Spot(name = "The open movie project", city = "Kyoto", url = "", postText = "", video = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"))

        return spots
    }


//
//    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
//
//        super.setUserVisibleHint(isVisibleToUser)
//
//        if (isVisibleToUser){
//            Log.i("UserVideoFragment---->",  " is resumed but not visible")
//        }
//        else{
//            Log.i("UserVideoFragment---->",  " is not visible")
//        }
//
//    }

    override fun onResume() {
        super.onResume()

        Log.i("UserVideoAdapter--->" , " is visible now")

        adapter = context?.let { CardStackVideoAdapter(it, createSpots()) }!!
        manager = CardStackLayoutManager(context,this)


        setupCardStackView()

        adapter?.viewHolder?.player?.playWhenReady
        adapter?.viewHolder?.playWhenReady = true

    }

    override fun onPause() {
        super.onPause()

        Log.i("The fragment------>", " is not visible to user")
        adapter?.viewHolder?.releasePlayer()
        adapter?.viewHolder?.player?.release()
    }


}