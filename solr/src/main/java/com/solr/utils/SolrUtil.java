package com.solr.utils;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.solr.entity.Person;

public class SolrUtil {
	//solr服务器所在的地址，core0为自己创建的文档库目录
	private final static String SOLR_URL = "http://192.168.4.224:8080/solr/core0";

	/**
	 * 获取客户端的连接
	 * 
	 * @return
	 */
	public HttpSolrClient createSolrServer() {
	    HttpSolrClient solr = null;
	    solr = new HttpSolrClient.Builder(SOLR_URL).withConnectionTimeout(10000).withSocketTimeout(60000).build();
	    return solr;
	}

	/**
	 * 往索引库添加文档
	 * 
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public void addDoc() throws SolrServerException, IOException {
	    HttpSolrClient solr = createSolrServer();
	    for(int i = 30; i<100000; i++) {
	    	System.out.println(i);
	    	SolrInputDocument document = new SolrInputDocument();
	    	document.addField("id", String.valueOf(i));
		    document.addField("name", "我谁的凯撒好可怜访客接口了冯伟峰咖啡菲菲姐我偶尔文件覅偶鞥见附件");
		    document.addField("description", "副科级而发科技费金额为覅金额见覅额偶文件放写文件放我偶发金额我就");
		    //document.addField("cheng", "啊成");
		    solr.add(document);
		    solr.commit();
		    System.out.println("添加成功"+String.valueOf(i));
	    }
	    solr.close();
	}

	/**
	 * 根据ID从索引库删除文档
	 * 
	 * @throws SolrServerException
	 * @throws IOException
	 */
	public void deleteDocumentById() throws SolrServerException, IOException {
	    HttpSolrClient server = createSolrServer();
	    server.deleteById("6");
	    server.commit();
	    server.close();
	}

	/**
	 * 根据设定的查询条件进行文档字段的查询
	 * @throws Exception
	 */
	public void querySolr() throws Exception {

	    HttpSolrClient server = new HttpSolrClient.Builder(SOLR_URL)
	                .withConnectionTimeout(10000)
	                .withSocketTimeout(60000).build();
	    SolrQuery query = new SolrQuery();

	    //下面设置solr查询参数

	    //query.set("q", "*:*");// 参数q  查询所有   
	    query.set("q", "成");//相关查询，比如某条数据某个字段含有周、星、驰三个字  将会查询出来 ，这个作用适用于联想查询

	    //参数fq, 给query增加过滤查询条件 
	    query.addFilterQuery("id:[0 TO 12]");
	    query.addFilterQuery("description:无"); 

	    //参数df,给query设置默认搜索域，从哪个字段上查找
	    //query.set("df", "description"); 

	    //参数sort,设置返回结果的排序规则
	    query.setSort("id",SolrQuery.ORDER.desc);

	    //设置分页参数
	    query.setStart(0);
	    query.setRows(10);

	    //设置高亮显示以及结果的样式
	    query.setHighlight(true);
	    query.addHighlightField("name");  
	    query.setHighlightSimplePre("<font color='red'>");  
	    query.setHighlightSimplePost("</font>"); 

	    //执行查询
	    QueryResponse response = server.query(query);

	    //获取返回结果
	    SolrDocumentList resultList = response.getResults();

	    for(SolrDocument document: resultList){
	        System.out.println("id:"+document.get("id")+"   document:"+document.get("name")+"    description:"+document.get("description"));
	    }

	    //获取实体对象形式
	    List<Person> persons = response.getBeans(Person.class);

	    //System.out.println(persons.get(0).getName());

	}

	public static void main(String[] args) throws Exception {
	    SolrUtil solr = new SolrUtil();
	    //solr.addDoc();
	    solr.querySolr();
	}
}
