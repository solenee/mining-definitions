package fr.lirmm.advanse.chv.definitionMining.uima.utils;

/**
 * Represent a Treetagger tag for chunks 
 * @author solenee
 *
 */
public class TreetaggerChunkTag {
	private String tag ;
	private String text = "";
	private int begin;
	private int end;
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag.trim();
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text.trim();
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	
	@Override
	public String toString() {
		return tag+" ("+begin+") ["+text+"]";
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
}
