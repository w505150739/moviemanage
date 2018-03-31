package com.movie.common.utils;

import com.movie.common.exception.GlobalException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HTTP工具类.
 * 
 * @author
 */
public class HttpUtil {

	private static Logger log = Logger.getLogger(HttpUtil.class);

	/** 默认编码方式 -UTF8 */
	private static final String DEFAULT_ENCODE = "utf-8";

	// 信任所有站点
	static {
	}

	/**
	 * 构造方法
	 */
	public HttpUtil() {
		// empty constructor for some tools that need an instance object of the
		// class
	}

	/**
	 * GET请求, 结果以字符串形式返回.
	 * 
	 * @param url
	 *            请求地址
	 * @return 内容字符串
	 */
	public static String getUrlAsString(String url) throws Exception {
		return getUrlAsString(url, null, DEFAULT_ENCODE);
	}

	/**
	 * GET请求, 结果以字符串形式返回.
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return 内容字符串
	 */
	public static String getUrlAsString(String url, Map<String, String> params) throws Exception {
		return getUrlAsString(url, params, DEFAULT_ENCODE);
	}

	/**
	 * GET请求, 结果以字符串形式返回.
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param encode
	 *            编码方式
	 * @return 内容字符串
	 */
	public static String getUrlAsString(String url, Map<String, String> params, String encode) throws Exception {
		// 开始时间
		long t1 = System.currentTimeMillis();
		// 获得HttpGet对象
		HttpGet httpGet = getHttpGet(url, params, encode);
		// 调试信息
		log.debug("url:" + url);
		log.debug("params:" + params.toString());
		log.debug("encode:" + encode);
		// 发送请求
		String result = executeHttpRequest(httpGet, null, encode);
		// 结束时间
		long t2 = System.currentTimeMillis();
		// 调试信息
		log.debug("result:" + result);
		log.debug("consume time:" + ((t2 - t1)));
		// 返回结果
		return result;
	}

	/**
	 * POST请求, 结果以字符串形式返回.
	 * 
	 * @param url
	 *            请求地址
	 * @return 内容字符串
	 */
	public static String postUrlAsString(String url) {
		return postUrlAsString(url, null, null, null);
	}

	/**
	 * <b>概要：</b> POST请求, 结果以字符串形式返回. <b>作者：</b>SUXH </br> <b>日期：</b>2015-5-14
	 * </br>
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return 内容字符串 返回相应的内容字符串 如出现org.apache.http.conn.ConnectTimeoutException:
	 *         Connect to www.birdfenqi.com:80 timed out 则返回空字符串
	 * @throws Exception
	 */
	public static String postUrlAsString(String url, Map<String, String> params) {
		return postUrlAsString(url, params, null, "UTF-8");
	}

	/**
	 * POST请求, 结果以字符串形式返回.
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param reqHeader
	 *            请求头内容
	 * @return 内容字符串
	 * @throws Exception
	 */
	public static String postUrlAsString(String url, Map<String, String> params, Map<String, String> reqHeader) {
		return postUrlAsString(url, params, reqHeader, null);
	}

	/**
	 * <b>概要：</b> POST请求, 结果以字符串形式返回. <b>作者：</b>SUXH </br> <b>日期：</b>2015-5-14
	 * </br>
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param reqHeader
	 *            请求头内容
	 * @param encode
	 *            编码方式
	 * @return 内容字符串 返回相应的内容字符串 如出现org.apache.http.conn.ConnectTimeoutException:
	 *         Connect to www.birdfenqi.com:80 timed out 则返回空字符串
	 */
	public static String postUrlAsString(String url, Map<String, String> params, Map<String, String> reqHeader, String encode) {
		long t1 = System.currentTimeMillis();// 开始时间
		HttpPost httpPost = getHttpPost(url, params, encode);// 获得HttpPost对象
		try {
			String result = executeHttpRequest(httpPost, reqHeader, encode);// 发送请求
			long t2 = System.currentTimeMillis();// 结束时间
			// 调试信息
			log.debug("url:" + url);
			log.debug("params:" + params.toString());
			log.debug("reqHeader:" + reqHeader);
			log.debug("encode:" + encode);
			log.debug("result:" + result);
			log.debug("consume time:" + ((t2 - t1)));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("url:" + url + "请求失败");
			throw new GlobalException("请求失败");
		}
	}

	public static String postUrlAsString(String url, String params, String encode) {
		log.info("params:" + params);
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			// 设置参数
			StringEntity entity = new StringEntity(params, encode);
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, encode);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * TODO 通过json请求url
	 * @author: liuyz
	 * @date: 2018年1月18日
	 * @param url
	 * @param paramsJson
	 * @param reqHeader
	 * @param encode
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String postUrlAsJson(String url, String paramsJson, Map<String, String> reqHeader, String encode) throws IllegalStateException, IOException {
		long t1 = System.currentTimeMillis();// 开始时间

		HttpPost httpPost = getHttpPostJson(url, paramsJson, encode);// 获得HttpPost对象
		String result = "";
//		reqHeader.put(HTTP.CONTENT_TYPE,"application/json");
		try {
			result = executeHttpRequest(httpPost, reqHeader, encode);// 发送请求
		} catch (Exception e) {
			e.printStackTrace();
			result = "";
			throw new GlobalException("请求出错");
		}
		long t2 = System.currentTimeMillis();// 结束时间
		// 返回结果
		return result;
	}
	
	private static HttpPost getHttpPostJson(String url, String params, String encode) {
		HttpPost httpPost = new HttpPost(url);
		if (params != null) {
			StringEntity entity = new StringEntity(params,"utf-8");
			entity.setContentType("application/json");
//				entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(entity);
		}
		return httpPost;
	}
	/**
	 * 获得HttpGet对象
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param encode
	 *            编码方式
	 * @return HttpGet对象
	 */
	public static HttpGet getHttpGet(String url, Map<String, String> params, String encode) {
		StringBuffer buf = new StringBuffer(url);
		if (params != null) {
			// 地址增加?或者&
			String flag = (url.indexOf('?') == -1) ? "?" : "&";
			// 添加参数
			for (String name : params.keySet()) {
				buf.append(flag);
				buf.append(name);
				buf.append("=");
				try {
					String param = params.get(name);
					if (param == null) {
						param = "";
					}
					buf.append(URLEncoder.encode(param, encode));
				} catch (UnsupportedEncodingException e) {
					log.error("URLEncoder Error,encode=" + encode + ",param=" + params.get(name), e);
				}
				flag = "&";
			}
		}
		HttpGet httpGet = new HttpGet(buf.toString());

		return httpGet;
	}

	/**
	 * 获得HttpPost对象
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param encode
	 *            编码方式
	 * @return HttpPost对象
	 */
	private static HttpPost getHttpPost(String url, Map<String, String> params, String encode) {
		HttpPost httpPost = new HttpPost(url);
		if (params != null) {
			List<NameValuePair> form = new ArrayList<NameValuePair>();
			for (String name : params.keySet()) {
				form.add(new BasicNameValuePair(name, params.get(name)));
			}
			try {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, encode);
				httpPost.setEntity(entity);
			} catch (UnsupportedEncodingException e) {
				log.error("UrlEncodedFormEntity Error,encode=" + encode + ",form=" + form, e);
			}
		}
		return httpPost;
	}

	/**
	 * <b>概要：</b> 执行HTTP请求 <b>作者：</b>SUXH </br> <b>日期：</b>2015-7-25 </br>
	 * 
	 * @param httpUriRequest
	 *            请求对象
	 * @param reqHeader
	 *            请求头 map
	 * @param encode
	 *            编码
	 * @return 响应字符串
	 * @throws Exception
	 */
	private static String executeHttpRequest(HttpUriRequest httpUriRequest, Map<String, String> reqHeader, String encode) throws Exception {
		HttpClient client = null;
		String result = null;
		try {
			// 创建HttpClient对象
			client = new DefaultHttpClient();

			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 366000);// 设置连接超时时间
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 366000);// 设置Socket超时时间
			if (reqHeader != null) {// 设置请求头信息
				for (String name : reqHeader.keySet()) {
					httpUriRequest.addHeader(name, reqHeader.get(name));
				}
			}
			HttpResponse response = client.execute(httpUriRequest);// 获得返回结果

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {// 如果成功
				result = EntityUtils.toString(response.getEntity(), encode);
			} else {// 如果失败
				StringBuffer errorMsg = new StringBuffer();
				errorMsg.append("httpStatus:");
				errorMsg.append(response.getStatusLine().getStatusCode());
				errorMsg.append(response.getStatusLine().getReasonPhrase());
				errorMsg.append(", Header: ");
				Header[] headers = response.getAllHeaders();
				for (Header header : headers) {
					errorMsg.append(header.getName());
					errorMsg.append(":");
					errorMsg.append(header.getValue());
				}
				log.error("HttpResonse Error:" + errorMsg);
			}
		} catch (Exception e) {
			log.error("http连接异常", e);
			throw new Exception("http连接异常");
		} finally {
			try {
				client.getConnectionManager().shutdown();
			} catch (Exception e) {
				log.error("finally HttpClient shutdown error", e);
			}
		}
		return result;
	}

	/**
	 * http 请求
	 * 
	 * @param httpUriRequest
	 * @param reqHeader
	 * @param encode
	 * @return HttpResponse
	 */
	public static HttpResponse getHttpReponse(HttpUriRequest httpUriRequest, Map<String, String> reqHeader, String encode) {
		HttpResponse httpResponse = null;
		HttpClient client = null;
		try {
			client = new DefaultHttpClient();

			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 366000);// 设置连接超时时间
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 366000);// 设置Socket超时时间
			if (reqHeader != null) {
				for (String name : reqHeader.keySet()) {
					httpUriRequest.addHeader(name, reqHeader.get(name));
				}
			}
			httpResponse = client.execute(httpUriRequest);
		} catch (ClientProtocolException e) {
			log.error("getHttpReponse --> ClientProtocolException", e);

		} catch (IOException e) {
			log.error("getHttpReponse --> IOException", e);
		} finally {
			if (null != client) {
				client.getConnectionManager().shutdown();
			}
		}
		return httpResponse;
	}

	/**
	 * <b>概要：</b> 下载文件保存到本地 <b>作者：</b>SUXH </br> <b>日期：</b>2015-3-14 </br>
	 * 
	 * @param path
	 *            文件保存位置
	 * @param url
	 *            文件地址
	 * @return
	 * @throws IOException
	 */
	public static boolean downloadFile(String path, String url) {
		log.info("path:" + path);
		log.info("url:" + url);
		HttpClient client = null;
		try {
			// 创建HttpClient对象
			client = new DefaultHttpClient();
			// 获得HttpGet对象
			HttpGet httpGet = getHttpGet(url, null, null);
			// 发送请求获得返回结果
			HttpResponse response = client.execute(httpGet);
			// 如果成功
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				byte[] result = EntityUtils.toByteArray(response.getEntity());
				BufferedOutputStream bw = null;
				try {
					// 创建文件对象
					File f = new File(path);
					if (f.exists())// 重复时候替换掉
						f.delete();
					// 创建文件路径
					if (!f.getParentFile().exists())
						f.getParentFile().mkdirs();
					// 写入文件
					bw = new BufferedOutputStream(new FileOutputStream(path));
					bw.write(result);
				} catch (Exception e) {
					log.error("保存文件错误,path=" + path + ",url=" + url, e);
					return false;
				} finally {
					try {
						if (bw != null)
							bw.close();
					} catch (Exception e) {
						log.error("finally BufferedOutputStream shutdown close", e);
					}
				}
			}
			// 如果失败
			else {
				StringBuffer errorMsg = new StringBuffer();
				errorMsg.append("httpStatus:");
				errorMsg.append(response.getStatusLine().getStatusCode());
				errorMsg.append(response.getStatusLine().getReasonPhrase());
				errorMsg.append(", Header: ");
				Header[] headers = response.getAllHeaders();
				for (Header header : headers) {
					errorMsg.append(header.getName());
					errorMsg.append(":");
					errorMsg.append(header.getValue());
				}
				log.error("HttpResonse Error:" + errorMsg);
				return false;
			}
		} catch (Exception e) {
			log.error("下载文件保存到本地,文件操作异常,path=" + path + ",url=" + url, e);
			throw new GlobalException("下载文件异常");
		} finally {
			try {
				client.getConnectionManager().shutdown();
			} catch (Exception e) {
				log.error("finally HttpClient shutdown error", e);
			}
		}
		return true;
	}
	
	// 以下为服务器端判断客户端浏览器类型的方法  
    public static String getBrowser(HttpServletRequest request) {  
        String UserAgent = request.getHeader("USER-AGENT").toLowerCase();  
        if (UserAgent != null) {  
            if (UserAgent.indexOf("msie") >= 0)  
                return "IE";  
            if(UserAgent.indexOf("trident") >= 0) //判断IE11：
            	//Mozilla/5.0 (Windows NT 6.3; Trident/7.0; rv 11.0) like Gecko
            	return "IE";
            if (UserAgent.indexOf("firefox") >= 0)  
                return "FF";  
            if (UserAgent.indexOf("safari") >= 0)  
                return "SF";  
        }  
        return null;  
    }  
}
