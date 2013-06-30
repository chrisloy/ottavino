package chrisloy.ottavino

import Implicits._
import javax.sound.midi.MidiSystem

object Main {
  
  def createSequencer(bpm: Int) = {
    val synth = MidiSystem.getSynthesizer()
    synth.open
    val chords = C(Major).seq.map(p => Note(p, 0, 67, 67)).map(n => Chord(n))
    val channel = synth.getChannels()(0)
    val sequence = new MidiSequence(channel, chords.toArray)
    new Sequencer(synth, bpm, sequence)
  }
}