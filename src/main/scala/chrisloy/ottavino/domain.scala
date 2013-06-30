package chrisloy.ottavino

object Implicits {
  // conversions
  implicit def toPitch(value: Int) = Pitch(value)
  implicit def toDuration(value: Long) = Duration(value)
  implicit def toWeight(value: Int) = Weight(value)
  implicit def fromPitch(value: Pitch) = value.pitch
  implicit def fromDuration(value: Duration) = value.millis
  implicit def fromWeight(value: Weight) = value.weight
  // imports
  type MidiSystem = javax.sound.midi.MidiSystem
}

import Implicits._

case class Pitch(pitch: Int)
case class Duration(millis: Long)
case class Weight(weight: Int)
case class Note(pitch: Pitch, duration: Duration, weight: Weight, decay: Weight)
case class Chord(notes: Note*)
case class Melody(notes: Note*)

sealed abstract class Key(val root: Pitch) {
  def apply(scale: Scale) = scale.relativePitches.map(p => p + root)
}

case object C extends Key(60)
case object Cs extends Key(61)
case object D extends Key(62)
case object Ds extends Key(63)
case object E extends Key(64)
case object F extends Key(65)
case object Fs extends Key(66)
case object G extends Key(67)
case object Gs extends Key(68)
case object A extends Key(69)
case object As extends Key(70)
case object B extends Key(71)

sealed abstract class Scale(val relativePitches: Pitch*)

case object Major extends Scale(0, 2, 4, 5, 7, 9, 11)
case object Minor extends Scale(0, 2, 3, 5, 7, 8, 11)

