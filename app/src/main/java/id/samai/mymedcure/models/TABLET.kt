package id.samai.mymedcure.models

class TABLET (val id: Int?, val title: String, val DATE: String, val morning: Int,val afternoon : Int,val evening : Int,val night: Int) {
    operator fun get(ink: String): Int {
        if (ink == "morning"){
            return morning
        }
        else if (ink == "afternoon"){
            return afternoon
        }
       else if (ink == "evening"){
            return evening
        }
        else {
            return night
        }

    }
}