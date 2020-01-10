package com.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;

import com.solr.utils.SolrUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    	SolrUtil s = new SolrUtil();
    	try {
			//s.querySolr();
    		s.addDoc();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
