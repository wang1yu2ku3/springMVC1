package com.wyl.test.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Words")
public class Dict {

	//主键,必填,数据库的自动增长字段生成@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    @Id
	private long id;
	
	@Column(name = "word")
	private String word;
	
	@Column(name = "meaning")
	private String meaning;
	
	@Column(name = "lx")
	private String example;
	
	@Column(name = "SYMBOLS_EN")
	private String symbolsEN;
	
	@Column(name = "SYMBOLS_US")
	private String symbolsUS;
	
	@Column(name="AUDIO_EN_MAN_URL")
	private String audio_en_man_url;
	
	@Column(name="AUDIO_EN_WOMAN_URL")
	private String audio_en_woman_url;
	
	@Column(name="AUDIO_US_MAN_URL")
	private String audio_us_man_url;
	
	@Column(name="AUDIO_US_WOMAN_URL")
	private String audio_us_woman_url;
	
	@Column(name = "flag")
	private String flag;
	
	@Column(name = "create_time")
	private Date createDate;
	
	@Column(name = "update_time")
	private Date updateDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getSymbolsEN() {
		return symbolsEN;
	}

	public void setSymbolsEN(String symbolsEN) {
		this.symbolsEN = symbolsEN;
	}

	public String getSymbolsUS() {
		return symbolsUS;
	}

	public void setSymbolsUS(String symbolsUS) {
		this.symbolsUS = symbolsUS;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getAudio_en_man_url() {
		return audio_en_man_url;
	}

	public void setAudio_en_man_url(String audio_en_man_url) {
		this.audio_en_man_url = audio_en_man_url;
	}

	public String getAudio_en_woman_url() {
		return audio_en_woman_url;
	}

	public void setAudio_en_woman_url(String audio_en_woman_url) {
		this.audio_en_woman_url = audio_en_woman_url;
	}

	public String getAudio_us_man_url() {
		return audio_us_man_url;
	}

	public void setAudio_us_man_url(String audio_us_man_url) {
		this.audio_us_man_url = audio_us_man_url;
	}

	public String getAudio_us_woman_url() {
		return audio_us_woman_url;
	}

	public void setAudio_us_woman_url(String audio_us_woman_url) {
		this.audio_us_woman_url = audio_us_woman_url;
	}
	
}
