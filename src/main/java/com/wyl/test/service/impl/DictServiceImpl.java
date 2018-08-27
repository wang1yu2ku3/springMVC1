package com.wyl.test.service.impl;


import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.wyl.test.dao.IDictDao;
import com.wyl.test.entity.Dict;
import com.wyl.test.service.IDictService;
import com.wyl.test.util.DownLoadUtil;
import com.wyl.test.util.RegHtml;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class DictServiceImpl extends HibernateBaseService<Dict, Long> implements IDictService{
	
	private static final Logger logger = Logger.getLogger(DictServiceImpl.class);
	
	@Autowired
	@Qualifier("dictDaoImpl")
	private IDictDao iDictDao;
	
	@Override
	public List<Dict> getAll() {
		return iDictDao.getAll();
	}
	
	@Override
	public List<Dict> queryByPrefix(String propertyName,String prefix) {
		return iDictDao.queryByPrefix(propertyName, prefix);
	}
	
	@Override
	public Dict queryWordByExactSearch(String word) {
		return iDictDao.queryWordByExactSearch(word);
	}
	
	private List<Dict> getAllDict(){
		List<Dict> list = iDictDao.getAllDictNotNull();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void getNetDict() {
		List<Dict> list = getAllDict();
		final WebClient webClient=new WebClient();
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setTimeout(6000);
		try {
			for(int i = 0 ; i < list.size() ; i++) {
				Dict dict = list.get(i);
				logger.info("---------------------------" + dict.getWord() + " START -------------------------------");
				logger.info("当前处理的单词 --->> " + dict.getWord());
				try {
                    final HtmlPage page=webClient.getPage("http://dict.cn/" + dict.getWord());
                    HtmlDivision div = (HtmlDivision) page.getByXPath("//div").get(4);
                    HtmlSpan span_en = (HtmlSpan) div.getByXPath("//span").get(0);
                    List<HtmlElement> list_Bdo_en = (List<HtmlElement>) span_en.getByXPath("//bdo");
                    if(list_Bdo_en.size() > 0){
                        HtmlElement t_en = list_Bdo_en.get(0);
                        DomNode bdo_en = (DomNode) span_en.getByXPath("//bdo").get(0);
                        if(t_en.getAttribute("lang").equals("EN-US")){
                            if(bdo_en != null){
                                dict.setSymbolsEN(bdo_en.getFirstChild().toString());
                                System.out.println(bdo_en.getFirstChild());
                            }
                        }else{
                            logger.info(dict.getWord() + "没有英式音标信息!");
                        }
                    } else {
                        logger.info(dict.getWord() + "没有英式音标信息!");
                    }


                    //更新英式音-女
                    if(Objects.nonNull(span_en.getByXPath("//i"))
                            && !span_en.getByXPath("//i").isEmpty()
                            && span_en.getByXPath("//i").size() > 1) {
                        DomNode en_audio_f = (DomNode) span_en.getByXPath("//i").get(0);
                        if(en_audio_f != null){
                            List<String> en_audio_f_list = RegHtml.match(en_audio_f.asXml().toString(), "i", "naudio");
                            if(en_audio_f_list!= null && en_audio_f_list.size() > 0){
                                String en_f_path = DownLoadUtil.downAudio(en_audio_f_list.get(0).toString(), dict.getWord(), "EN_F");
                                dict.setAudio_en_woman_url(en_f_path);
                                System.out.println(en_audio_f_list);
                            }else{
                                logger.info(dict.getWord() + "没有英式女生发音文件!");
                            }

                        }else{
                            logger.info(dict.getWord() + "没有英式女生发音文件!");
                        }
                    }

                    //更新英式音-男
                    if(Objects.nonNull(span_en.getByXPath("//i"))
                            && !span_en.getByXPath("//i").isEmpty()
                            && span_en.getByXPath("//i").size() > 2) {
                        DomNode en_audio_m = (DomNode) span_en.getByXPath("//i").get(1);
                        if(en_audio_m != null){
                            List<String> en_audio_m_list = RegHtml.match(en_audio_m.asXml().toString(), "i", "naudio");
                            if(en_audio_m_list != null && en_audio_m_list.size() > 0){
                                String en_m_path = DownLoadUtil.downAudio(en_audio_m_list.get(0).toString(), dict.getWord(), "EN_M");
                                dict.setAudio_en_man_url(en_m_path);
                                System.out.println(en_audio_m_list);
                            }else{
                                logger.info(dict.getWord() + "没有英式男生发音文件!");
                            }

                        } else {
                            logger.info(dict.getWord() + "没有英式男生发音文件!");
                        }
                    }

                    if(Objects.nonNull(div.getByXPath("//span"))
                            && !div.getByXPath("//span").isEmpty()) {
                        HtmlSpan span_us = (HtmlSpan) div.getByXPath("//span").get(1);
                        if(div.getByXPath("//span").size() > 2 ) {
                            List<HtmlElement> list_Bdo_us = (List<HtmlElement>) span_us.getByXPath("//bdo");
                            if(list_Bdo_us.size() > 0){
                                HtmlElement t_us = list_Bdo_us.get(0);
                                DomNode bdo_us = (DomNode) span_us.getByXPath("//bdo").get(0);
                                if(t_us.getAttribute("lang").equals("EN-US")){
                                    //更新美式应标音标
                                    if(bdo_us != null){
                                        dict.setSymbolsUS(bdo_us.getFirstChild().toString());
                                        System.out.println(bdo_us.getFirstChild());
                                    }
                                }else{
                                    logger.info(dict.getWord() + "没有美式音标信息!");
                                }
                            }else{
                                logger.info(dict.getWord() + "没有美式音标信息!");
                            }
                        }

                        if(span_us.getByXPath("//i").size() >= 3 ) {
                            //更新美式音-女
                            DomNode us_audio_f = (DomNode) span_us.getByXPath("//i").get(2);
                            if(us_audio_f != null){
                                List<String> us_audio_f_list = RegHtml.match(us_audio_f.asXml().toString(), "i", "naudio");
                                if(us_audio_f_list != null && us_audio_f_list.size() > 0){
                                    String us_f_path = DownLoadUtil.downAudio(us_audio_f_list.get(0).toString(), dict.getWord(), "US_F");
                                    dict.setAudio_us_woman_url(us_f_path);
                                    System.out.println(us_audio_f_list);
                                }else{
                                    logger.info(dict.getWord() + "没有美式女生发音文件!");
                                }

                            }else{
                                logger.info(dict.getWord() + "没有美式女生发音文件!");
                            }
                        }

                        if(span_us.getByXPath("//i").size() >= 4 ) {
                            //更新美式音-男
                            DomNode us_audio_m = (DomNode) span_us.getByXPath("//i").get(3);
                            if(us_audio_m != null){
                                List<String> us_audio_m_list = RegHtml.match(us_audio_m.asXml().toString(), "i", "naudio");
                                if(us_audio_m_list != null && us_audio_m_list.size() > 0){
                                    String us_m_path = DownLoadUtil.downAudio(us_audio_m_list.get(0).toString(), dict.getWord(), "US_M");
                                    dict.setAudio_us_man_url(us_m_path);
                                    System.out.println(us_audio_m_list);
                                } else {
                                    logger.info(dict.getWord() + "没有美式男生发音文件!");
                                }

                            } else {
                                logger.info(dict.getWord() + "没有美式男生发音文件!");
                            }
                        }
                    }

                    //System.out.println(div_html);
                    dict.setFlag("SUCCESSFUL");
                    dict.setUpdateDate(new Date());
                    iDictDao.update(dict);
                    logger.info("---------------------------" + dict.getWord() + " END  ---------------------------------");
                } catch (SocketTimeoutException e) {
                    //logger.info("处理单词 : {} 时超时异常 : {}", dict.getWord(), e.getMessage());
                    e.printStackTrace();
                }
			}
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(webClient != null){
				webClient.closeAllWindows();
			}
		}
	}
}
