package chrisloy.ottavino

import Implicits._

import javax.sound.midi.MidiChannel
import javax.sound.midi.Synthesizer
import javax.sound.midi.Instrument

case class MidiSequence(channel: MidiChannel, chords: Array[Chord]) {
  
  def this(channel: MidiChannel, chords: Chord*) = this(channel, chords.toArray)

  var on = false;

  def on(position: Int) {
    val chord = chords(position % chords.length)
    if (chord != null && !on) {
      start(channel, chord)
      on = true
    }
  }

  def off(position: Int) {
    val chord = chords(position % chords.length)
    val nextChord = chords((position + 1) % chords.length)
    if (chord != null && !chord.equals(nextChord)) {
      stop(channel, chord)
      on = false
    }
  }

  def start(channel: MidiChannel, chord: Chord) = {
    chord.notes.foreach(n => if (n != null) channel.noteOn(n.pitch, n.weight))
  }

  def stop(channel: MidiChannel, chord: Chord) = {
    chord.notes.foreach(n => if (n != null) channel.noteOff(n.pitch, n.decay))
  }
}