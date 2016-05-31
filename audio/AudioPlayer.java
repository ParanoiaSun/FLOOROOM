package audio;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 
 * 
 *
 */
public class AudioPlayer implements Runnable{

	private AudioFormat format;
	private byte[] samples;
	private boolean stop;

	public AudioPlayer(String filename) {
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(new File(filename));
			format = stream.getFormat();
			samples = getSamples(stream);
		} catch (UnsupportedAudioFileException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		new Thread(this).start();
	}

	/**
	 * 
	 * @param string
	 * @param i
	 */
	public AudioPlayer(String string, int i) {
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(new File(
					string));
			format = stream.getFormat();
			samples = getSamples(stream);
		} catch (UnsupportedAudioFileException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		new Thread(new LoopPlay()).start();
	}

	public byte[] getSamples() {
		return samples;
	}

	private byte[] getSamples(AudioInputStream audioStream) {
		int length = (int) (audioStream.getFrameLength() * format
				.getFrameSize());
		byte[] samples = new byte[length];
		DataInputStream is = new DataInputStream(audioStream);
		try {
			is.readFully(samples);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return samples;
	}

	private void audioPlay(InputStream source) {
		stop=false;
		int bufferSize = format.getFrameSize()
				* Math.round(format.getSampleRate() / 10);
		byte[] buffer = new byte[bufferSize];
		SourceDataLine line;
		try {
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(format, bufferSize);
		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
			return;
		}
		line.start();
		try {
			int numBytesRead = 0;
			while (numBytesRead != -1 && !stop) {
				numBytesRead = source.read(buffer, 0, buffer.length);
				if (numBytesRead != -1) {
					line.write(buffer, 0, numBytesRead);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		line.drain();
		line.close();
	}
	
	public void stop(){
		stop=true;
	}

	@Override
	public void run() {
		InputStream stream = new ByteArrayInputStream(this.getSamples());
		System.out.println("stream");
		this.audioPlay(stream);
		
	}
	
	class LoopPlay implements Runnable{

		@Override
		public void run() {
			while(!stop){
			InputStream stream = new ByteArrayInputStream(getSamples());
			audioPlay(stream);
			}
		}
		
		
	}

}
