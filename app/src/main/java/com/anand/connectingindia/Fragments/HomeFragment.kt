package com.anand.connectingindia.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anand.connectingindia.Adapter.CardStackAdapter
import com.anand.connectingindia.Adapter.CommentAdapter
import com.anand.connectingindia.Callback.SpotDiffCallback
import com.anand.connectingindia.Model.Comments
import com.anand.connectingindia.Model.Spot
import com.anand.connectingindia.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yuyakaido.android.cardstackview.*


class HomeFragment : Fragment(), CardStackListener {

    private lateinit var cardStackView : CardStackView
    private lateinit var toolbar: Toolbar
    private lateinit var manager: CardStackLayoutManager
    private val adapter by lazy { CardStackAdapter(createSpots()) }
    private lateinit var rewind : FloatingActionButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val homeFragmentView : View = inflater!!.inflate(R.layout.fragment_home, container, false)

        toolbar = homeFragmentView.findViewById(R.id.toolbar) as Toolbar
        rewind = homeFragmentView.findViewById(R.id.rewind)

        cardStackView = homeFragmentView.findViewById(R.id.cardStackView)
        manager = CardStackLayoutManager(context,this)

        setupCardStackView()

        rewind.setOnClickListener {
            cardStackView.rewind()
        }

        return homeFragmentView
    }



    override fun onCardDragging(direction: Direction, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction) {
        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")

        if (manager.topPosition == adapter.itemCount - 5) {
            paginate()
        }
    }

    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")
    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }

    override fun onCardAppeared(view: View, position: Int) {
//        val textView = view.findViewById<TextView>(com.yuyakaido.android.cardstackview.R.id.item_name)
//        val textView = view.findViewById<TextView>(R.id.item_name)
//        Log.d("CardStackView", "onCardAppeared: ($position) ${textView.text}")
    }

    override fun onCardDisappeared(view: View, position: Int) {
//        val textView = view.findViewById<TextView>(com.yuyakaido.android.cardstackview.R.id.item_name)
        val textView = view.findViewById<TextView>(R.id.item_name)
        Log.d("CardStackView", "onCardDisappeared: ($position) ${textView.text}")
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
        spots.add(Spot(name = "Yasaka Shrine", city = "Kyoto", url = "https://source.unsplash.com/Xq1ntWruZQI/600x800", postText = "", video = ""))
        spots.add(Spot(name = "Fushimi Inari Shrine", city = "Kyoto", url = "https://source.unsplash.com/NYyCqdBOKwc/600x800", postText = "",video = ""))

        spots.add(Spot(name = "Fushimi Inari Shrine", city = "Kyoto", url = "", postText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Mauris finibus sollicitudin augue, eu volutpat eros fringilla vel. Duis mattis, erat at cursus cursus, " +
                "ante purus tincidunt magna, vitae sollicitudin tortor erat non dolor. Cras mi elit, rutrum ac feugiat vitae, " +
                "imperdiet nec eros. Vivamus aliquam felis quam. Nunc sit amet ante nec sapien tristique iaculis id quis neque. " +
                "Integer maximus neque sit amet iaculis tristique. Maecenas ac blandit elit. ", video = ""))

//        spots.add(Spot(name = "Bamboo Forest", city = "Kyoto", url = "https://source.unsplash.com/buF62ewDLcQ/600x800", postText = "", video = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))
        spots.add(Spot(name = "Brooklyn Bridge", city = "New York", url = "https://source.unsplash.com/THozNzxEP3g/600x800", postText = "", video = ""))
        spots.add(Spot(name = "Empire State Building", city = "New York", url = "https://source.unsplash.com/USrZRcRS2Lw/600x800", postText = "", video = ""))
        spots.add(Spot(name = "The statue of Liberty", city = "New York", url = "https://source.unsplash.com/PeFk7fzxTdk/600x800", postText = "", video = ""))
        spots.add(Spot(name = "Louvre Museum", city = "Paris", url = "https://source.unsplash.com/LrMWHKqilUw/600x800", postText = "", video = ""))
        spots.add(Spot(name = "Eiffel Tower", city = "Paris", url = "https://source.unsplash.com/HN-5Z6AmxrM/600x800", postText = "", video = ""))
        spots.add(Spot(name = "Big Ben", city = "London", url = "https://source.unsplash.com/CdVAUADdqEc/600x800", postText = "", video = ""))
        spots.add(Spot(name = "Great Wall of China", city = "China", url = "https://source.unsplash.com/AWh9C-QjhE4/600x800", postText = "", video = ""))
        return spots
    }



}
