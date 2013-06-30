package chrisloy.ottavino

import javax.sound.midi.Synthesizer

class Sequencer(val synth: Synthesizer, val bpm: Long, val sequences: MidiSequence*) {
  val interval = 60000 / bpm;
  
 def play(beats: Int) = {
   (0 until beats) map {i =>
     println("Iteration " + i)
     sequences.foreach(s => s.on(i))
     Thread.sleep(interval)
     sequences.foreach(s => s.off(i))
   }
   Thread.sleep(interval)
 }
}