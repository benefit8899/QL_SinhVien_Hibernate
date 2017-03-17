package com.vanloi.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PageParameter {
	
	private List<Integer> lstPage = new ArrayList<Integer>();
	private int pageIndex = 0;
	public List<Integer> getLstPage() {
		processListPage();
		return lstPage;
	}
	public void setLstPage(List<Integer> lstPage) {
		this.lstPage = lstPage;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageLength() {
		return pageLength;
	}
	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	private int pageLength = 0;
	private int pageNumber = 0;
	private void processListPage(){
		lstPage.clear();
		int countUp = 0;
		int countDown = 0;
		int ip = 0;
		int id = 0;
		for(ip = pageIndex; ip <= pageLength && countUp <= pageNumber / 2; ip++){
			lstPage.add(ip);
			countUp++;
		}
		for(id = pageIndex - 1; id > 0 && countDown < pageNumber / 2; id--){
			lstPage.add(id);
			countDown++;
		}
		if(countDown < (int)(pageNumber / 2)){
			for(; ip <= pageLength && countUp < pageNumber - countDown; ip++){
				lstPage.add(ip);
				countUp++;
			}
		}else{
			for(; id > 0 && countDown < pageNumber - countUp; id--){
				lstPage.add(id);
				countDown++;
			}
		}
		Collections.sort(lstPage);
	}
}
