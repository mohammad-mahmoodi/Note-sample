package tdroid.note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import tdroid.note.theme.BabyBlue
import tdroid.note.theme.LightGreen
import tdroid.note.theme.RedOrange
import tdroid.note.theme.RedPink
import tdroid.note.theme.Violet

@Entity
data class Note(
  val title : String,
  val content: String,
  val timestamp: Long,
  val color: Int,
  @PrimaryKey val id: Int? = null
){
    companion object{
      val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message:String): Exception(message)
