package com.dengjintian.weibo2rss.weibo4j;

import java.util.List;
import com.dengjintian.weibo2rss.weibo4j.http.ImageItem;
import com.dengjintian.weibo2rss.weibo4j.model.Emotion;
import com.dengjintian.weibo2rss.weibo4j.model.Paging;
import com.dengjintian.weibo2rss.weibo4j.model.PostParameter;
import com.dengjintian.weibo2rss.weibo4j.model.Status;
import com.dengjintian.weibo2rss.weibo4j.model.StatusWapper;
import com.dengjintian.weibo2rss.weibo4j.model.WeiboException;
import com.dengjintian.weibo2rss.weibo4j.org.json.JSONArray;
import com.dengjintian.weibo2rss.weibo4j.org.json.JSONObject;
import com.dengjintian.weibo2rss.weibo4j.util.WeiboConfig;

public class Timeline extends Weibo{

	/*----------------------------????----------------------------------------*/

	/**
	 *
	 */
	private static final long serialVersionUID = 6235150828015082132L;

	/**
	 * ?????????
	 *
	 * @return list of statuses of the Public Timeline
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/public_timeline">statuses/public_timeline
	 *      </a>
	 * @since JDK 1.5
	 */
	public StatusWapper getPublicTimeline() throws WeiboException {
		return Status.constructWapperStatus(client.get(WeiboConfig
				.getValue("baseURL") + "statuses/public_timeline.json"));
	}

	/**
	 * ?????????
	 *
	 * @param count
	 *            ?????????????20?
	 * @param baseApp
	 *            ???????????????0????1??????
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/public_timeline">statuses/public_timeline
	 *      </a>
	 * @since JDK 1.5
	 */
	public StatusWapper getPublicTimeline(int count, int baseApp) throws WeiboException {
		return Status.constructWapperStatus(client.get(
				WeiboConfig.getValue("baseURL") + "statuses/public_timeline.json", new PostParameter[] {
						new PostParameter("count", count),
						new PostParameter("base_app", baseApp) }));

	}

	/**
	 * ??????????????????20??????
	 * ????? http://weibo.com ??“????”?????????
	 * This method calls
	 * http://api.t.sina.com.cn/statuses/friends_timeline.format
	 *
	 * @return list of the Friends Timeline
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/statuses/friends_timeline">
	 *      statuses/friends_timeline </a>
	 * @since JDK 1.5
	 */
	public StatusWapper getFriendsTimeline() throws WeiboException {
		return Status.constructWapperStatus(client.get(WeiboConfig.getValue("baseURL") + "statuses/friends_timeline.json"));

	}

	/**
	 * ???????????????????????<br/>
	 * ????? http://weibo.com ??“????”?????????
	 *
	 * @param paging
	 *            ??????
	 * @param ????ID?0????1????2????3????4???????0?
	 * @return list of the Friends Timeline
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/statuses/friends_timeline">
	 *      statuses/friends_timeline </a>
	 * @since JDK 1.5
	 */
	public StatusWapper getFriendsTimeline(Integer baseAPP, Integer feature,
			Paging paging) throws WeiboException {
		return Status.constructWapperStatus(client.get(
				WeiboConfig.getValue("baseURL") + "statuses/friends_timeline.json",
				new PostParameter[] {
						new PostParameter("base_app", baseAPP.toString()),
						new PostParameter("feature", feature.toString()) },
				paging));
		}
	/**
	 * ??????????????????20??????
	 * ????? http://weibo.com ??“????”?????????
	 * This method calls
	 * http://api.t.sina.com.cn/statuses/friends_timeline.format
	 *
	 * @return list of the Friends Timeline
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/statuses/friends_timeline/ids">
	 *      statuses/friends_timeline/ids </a>
	 * @since JDK 1.5
	 */
	public JSONObject getFriendsTimelineIds() throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "statuses/friends_timeline/ids.json").asJSONObject();

	}
	public JSONObject getFriendsTimelineIds(Integer baseAPP, Integer feature,
			Paging paging) throws WeiboException {
		return client.get(
				WeiboConfig.getValue("baseURL") + "statuses/friends_timeline/ids.json",
				new PostParameter[] {
						new PostParameter("base_app", baseAPP.toString()),
						new PostParameter("feature", feature.toString()) },
				paging).asJSONObject();
		}
	/**
	 * ???????????????????????<br/>
	 * ????? http://weibo.com ??“????”?????????
	 *
	 * @return list of status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/statuses/home_timeline">
	 *      statuses/home_timeline </a>
	 * @since JDK 1.5
	 */
	public StatusWapper getHomeTimeline() throws WeiboException {
		return Status.constructWapperStatus(client.get(WeiboConfig
				.getValue("baseURL") + "statuses/home_timeline.json"));

	}

	/**
	 * ???????????????????????<br/>
	 * ????? http://weibo.com ??“????”?????????
	 *
	 * @param paging
	 *            ??????
	 * @param ????ID
	 *            ?0????1????2????3????4???????0?
	 * @return list of the Friends Timeline
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a href="http://open.weibo.com/wiki/2/Statuses/home_timeline">
	 *      statuses/home_timeline </a>
	 * @since JDK 1.5
	 */
	public StatusWapper getHomeTimeline(Integer baseAPP, Integer feature,
			Paging paging) throws WeiboException {
		return Status
				.constructWapperStatus(client.get(
						WeiboConfig.getValue("baseURL") + "statuses/home_timeline.json",
						new PostParameter[] {
								new PostParameter("base_app", baseAPP.toString()),
								new PostParameter("feature", feature.toString()) },
						paging));
	}

	/**
	 * ???????????????
	 *
	 * @return list of the user_timeline
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/user_timeline">statuses/user_timeline</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getUserTimeline() throws WeiboException {
		return Status.constructWapperStatus(client.get(WeiboConfig
				.getValue("baseURL") + "statuses/user_timeline.json"));
	}
	public StatusWapper getUserTimelineByUid(String uid) throws WeiboException {
		return Status.constructWapperStatus(client.get(WeiboConfig
				.getValue("baseURL") + "statuses/user_timeline.json",new PostParameter[]{
			new PostParameter("uid", uid)
		}));
	}
	public StatusWapper getUserTimelineByName(String screen_name) throws WeiboException {
		return Status.constructWapperStatus(client.get(WeiboConfig
				.getValue("baseURL") + "statuses/user_timeline.json",new PostParameter[]{
			new PostParameter("screen_name", screen_name)
		}));
	}
	/**
	 * ???????????????
	 *
	 * @param uid
	 *            ???????ID?
	 * @param screen_name
	 *            ??????????
	 * @param count
	 *            ?????????????50?
	 * @param page
	 *            ???????????1?
	 * @param base_app
	 *            ?????????????0?????????1?????????????0?
	 * @param feature
	 *            ????ID?0????1????2????3????4???????0?
	 * @return list of the user_timeline
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/user_timeline">statuses/user_timeline</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getUserTimelineByUid(String uid, Paging page,
			Integer base_app, Integer feature) throws WeiboException {
		return Status.constructWapperStatus(client.get(
						WeiboConfig.getValue("baseURL")	+ "statuses/user_timeline.json",
						new PostParameter[] {
								new PostParameter("uid", uid),
								new PostParameter("base_app", base_app.toString()),
								new PostParameter("feature", feature.toString()) },
						page));
	}
	public StatusWapper getUserTimelineByName(String screen_name, Paging page,Integer base_app, Integer feature) throws WeiboException {
		return Status.constructWapperStatus(client.get(
						WeiboConfig.getValue("baseURL")	+ "statuses/user_timeline.json",
						new PostParameter[] {
								new PostParameter("screen_name", screen_name),
								new PostParameter("base_app", base_app.toString()),
								new PostParameter("feature", feature.toString()) },
						page));
	}
	/**
	 * ???????????????ID
	 *
	 * @return user_timeline IDS
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/user_timeline">statuses/user_timeline</a>
	 * @since JDK 1.5
	 */
	public JSONObject getUserTimelineIdsByUid(String uid, String sinceId) throws WeiboException{
		return client.get(WeiboConfig.getValue("baseURL")+"statuses/user_timeline/ids.json",new PostParameter[]{
			new PostParameter("uid", uid),new PostParameter("since_id",sinceId)
		}).asJSONObject();
	}
	public JSONObject getUserTimelineIdsByName(String screen_name) throws WeiboException{
		return client.get(WeiboConfig.getValue("baseURL")+"statuses/user_timeline/ids.json",new PostParameter[]{
			new PostParameter("screen_name", screen_name)
		}).asJSONObject();
	}
	/**
	 * ?????????????
	 *
	 * @param id
	 *            ???????ID
	 * @return list of Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/repost_timeline">statuses/repost_timeline</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getRepostTimeline(String id) throws WeiboException {
		return Status.constructWapperStatus(client.get(
				WeiboConfig.getValue("baseURL")
						+ "statuses/repost_timeline.json",
				new PostParameter[] { new PostParameter("id", id) }));
	}

	/**
	 * ?????????????
	 *
	 * @param id
	 *            ???????ID
	 * @param count
	 *            ?????????????50
	 * @param page
	 *            ???????????1
	 * @return list of Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/repost_timeline">statuses/repost_timeline</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getRepostTimeline(String id, Paging page)
			throws WeiboException {
		return Status.constructWapperStatus(client.get(
				WeiboConfig.getValue("baseURL")
						+ "statuses/repost_timeline.json",
				new PostParameter[] { new PostParameter("id", id) }, page));
	}
	/**
	 * ?????????????
	 *
	 * @param id
	 *            ???????ID
	 * @return ids
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/repost_timeline/ids">statuses/repost_timeline/ids</a>
	 * @since JDK 1.5
	 */
	public JSONObject getRepostTimelineIds(String id) throws WeiboException {
		return client.get(
				WeiboConfig.getValue("baseURL") + "statuses/repost_timeline/ids.json",
				new PostParameter[] { new PostParameter("id", id) }).asJSONObject();
	}
	/**
	 * ???????????????
	 *
	 * @return list of Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/repost_by_me">statuses/repost_by_me</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getRepostByMe() throws WeiboException {
		return Status.constructWapperStatus(client.get(WeiboConfig
				.getValue("baseURL") + "statuses/repost_by_me.json"));
	}

	/**
	 * ???????????????
	 *
	 * @param page
	 *            ???????????1
	 * @return list of Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/repost_by_me">statuses/repost_by_me</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getRepostByMe(Paging page) throws WeiboException {
		return Status.constructWapperStatus(client.get(
				WeiboConfig.getValue("baseURL") + "statuses/repost_by_me.json",null, page));
	}

	/**
	 * ??????????????????@????
	 *
	 * @return list of Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/mentions">statuses/mentions</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getMentions() throws WeiboException {
		return Status.constructWapperStatus(client.get(WeiboConfig
				.getValue("baseURL") + "statuses/mentions.json"));
	}

	/**
	 * ??????????????????@????
	 *
	 * @param count
	 *            ?????????????50?
	 * @param page
	 *            ???????????1?
	 * @param filter_by_author
	 *            ???????0????1???????2????????0?
	 * @param filter_by_source
	 *            ???????0????1??????2?????????0?
	 * @param filter_by_type
	 *            ???????0??????1??????????0?
	 * @return list of Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/mentions">statuses/mentions</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getMentions(Paging page, Integer filter_by_author,
			Integer filter_by_source, Integer filter_by_type)
			throws WeiboException {
		return Status.constructWapperStatus(client.get(
				WeiboConfig.getValue("baseURL") + "statuses/mentions.json",
				new PostParameter[] {
						new PostParameter("filter_by_author", filter_by_author.toString()),
						new PostParameter("filter_by_source", filter_by_source.toString()),
						new PostParameter("filter_by_type", filter_by_type.toString()) }, page));
	}
	/**
	 * ??????????????ID????@????
	 *
	 * @return list of Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/mentions/ids">statuses/mentions/ids</a>
	 * @since JDK 1.5
	 */
	public JSONObject getMentionsIds() throws WeiboException {
		return client.get(WeiboConfig
				.getValue("baseURL") + "statuses/mentions/ids.json").asJSONObject();
	}
	public JSONObject getMentionsIds(Paging page, Integer filter_by_author,
			Integer filter_by_source, Integer filter_by_type)
			throws WeiboException {
		return client.get(
				WeiboConfig.getValue("baseURL") + "statuses/mentions/ids.json",
				new PostParameter[] {
						new PostParameter("filter_by_author", filter_by_author.toString()),
						new PostParameter("filter_by_source", filter_by_source.toString()),
						new PostParameter("filter_by_type", filter_by_type.toString()) }, page).asJSONObject();
	}
	/**
	 * ?????????????
	 *
	 * @return list of Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/bilateral_timeline">statuses/bilateral_timeline</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getBilateralTimeline() throws WeiboException{
		return Status.constructWapperStatus(client.get(WeiboConfig.getValue("baseURL")+"statuses/bilateral_timeline.json"));
	}
	public StatusWapper getBilateralTimeline(Integer base_app,Integer feature) throws WeiboException{
		return Status.constructWapperStatus(client.get(WeiboConfig.getValue("baseURL")+"statuses/bilateral_timeline.json",
				new PostParameter[]{
			new PostParameter("base_app", base_app),
			new PostParameter("feature",feature)
		}));
	}
	/**
	 * ????ID????????
	 *
	 * @param id
	 *            ???????ID?
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/show">statuses/show</a>
	 * @since JDK 1.5
	 */
	public Status showStatus(String id) throws WeiboException {
		return new Status(client.get(WeiboConfig.getValue("baseURL")
				+ "statuses/show.json",
				new PostParameter[] { new PostParameter("id", id) }));
	}

	/**
	 * ????ID???MID
	 *
	 * @param id
	 *            ???????ID????????????????????20??
	 * @param type
	 *            ?????1????2????3???????1?
	 * @return Status's mid
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/querymid">statuses/querymid</a>
	 * @since JDK 1.5
	 */
	public JSONObject QueryMid(Integer type, String id) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "statuses/querymid.json",
				new PostParameter[] { new PostParameter("id", id),
						new PostParameter("type", type.toString()) }).asJSONObject();
	}
	/**
	 * ????ID???MID
	 *
	 * @param id
	 *            ???????ID????????????????????20??
	 * @param type
	 *            ?????1????2????3???????1?
	 * @param is_batch
	 *            ?????????0???1??????0?
	 * @return Status's mid
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/querymid">statuses/querymid</a>
	 * @since JDK 1.5
	 */
	public JSONObject QueryMid(Integer type, String id,int is_batch) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "statuses/querymid.json",
				new PostParameter[] { new PostParameter("id", id),
						new PostParameter("type", type.toString()),
						new PostParameter("is_batch", is_batch)}).asJSONObject();
	}
	/**
	 * ????MID???ID
	 *
	 * @param mid
	 *            true string ???????MID????????????????????20?
	 * @param type
	 *            ?????1????2????3???????1?
	 * @return Status's id
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/queryid">statuses/queryid</a>
	 * @since JDK 1.5
	 */
	public JSONObject QueryId(String mid, Integer type,int isBase62) throws WeiboException {
		return client.get(
				WeiboConfig.getValue("baseURL") + "statuses/queryid.json",
				new PostParameter[] { new PostParameter("mid", mid),
						new PostParameter("type", type.toString()),
						new PostParameter("isBase62", isBase62)}).asJSONObject();
	}

	/**
	 * ????MID???ID
	 *
	 * @param mid
	 *            true string ???????MID????????????????????20?
	 * @param type
	 *            ?????1????2????3???????1?
	 * @param is_batch
	 *            ?????????0???1??????0?
	 * @param inbox
	 *            ????????MID???????????0?????1????????0 ?
	 * @param isBase62
	 *            MID???base62???0???1??????0?
	 * @return Status's id
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/queryid">statuses/queryid</a>
	 * @since JDK 1.5
	 */
	public JSONObject QueryId(String mid, Integer type, Integer isBatch,Integer isBase62) throws WeiboException {
		return client.get(
				WeiboConfig.getValue("baseURL") + "statuses/queryid.json",
				new PostParameter[] { new PostParameter("mid", mid),
						new PostParameter("type", type.toString()),
						new PostParameter("is_batch", isBatch.toString()),
						new PostParameter("isBase62", isBase62.toString()) }).asJSONObject();
	}



	/**
	 * ????????????????
	 *
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/hot/repost_daily">statuses/hot/repost_daily</a>
	 * @since JDK 1.5
	 */
	public JSONArray getRepostDaily() throws WeiboException {
		return client.get(WeiboConfig
				.getValue("baseURL") + "statuses/hot/repost_daily.json").asJSONArray();
	}
	/**
	 * ????????????????
	 *
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/hot/repost_daily">statuses/hot/repost_daily</a>
	 * @since JDK 1.5
	 */
	public JSONArray getRepostWeekly() throws WeiboException {
		return client.get(WeiboConfig
				.getValue("baseURL") + "statuses/hot/repost_weekly.json").asJSONArray();
	}
	/**
	 * ????????????????
	 *
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/hot/repost_daily">statuses/hot/repost_daily</a>
	 * @since JDK 1.5
	 */
	public JSONArray getCommentsDaily() throws WeiboException {
		return client.get(WeiboConfig
				.getValue("baseURL") + "statuses/hot/comments_daily.json").asJSONArray();
	}
	/**
	 * ????????????????
	 *
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/hot/repost_daily">statuses/hot/repost_daily</a>
	 * @since JDK 1.5
	 */
	public JSONArray getCommentsWeekly() throws WeiboException {
		return client.get(WeiboConfig
				.getValue("baseURL") + "statuses/hot/comments_weekly.json").asJSONArray();
	}

	/**
	 * ???????
	 *
	 * @param id
	 *            ??????ID
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/repost">statuses/repost</a>
	 * @since JDK 1.5
	 */
	public Status Repost(String id) throws WeiboException {
		return new Status(client.post(WeiboConfig.getValue("baseURL")
				+ "statuses/repost.json",
				new PostParameter[] { new PostParameter("id", id) }));
	}

	/**
	 * ??????
	 *
	 * @param id
	 *            ??????ID
	 * @param status
	 *            ???????????URLencode??????140??????????“????”
	 * @param is_comment
	 *            ?????????????0???1?????????2????????3????????0
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/repost">statuses/repost</a>
	 * @since JDK 1.5
	 */
	public Status Repost(String id, String status, Integer is_comment)
			throws WeiboException {
		return new Status(client.post(WeiboConfig.getValue("baseURL") + "statuses/repost.json", new PostParameter[] {
				new PostParameter("id", id),
				new PostParameter("status", status),
				new PostParameter("is_comment", is_comment.toString()) }));
	}

	/**
	 * ????ID??????
	 *
	 * @param id
	 *            ???????ID
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/destroy">statuses/destroy</a>
	 * @since JDK 1.5
	 */
	public Status Destroy(String id) throws WeiboException {
		return new Status(client.post(WeiboConfig.getValue("baseURL")
				+ "statuses/destroy.json",
				new PostParameter[] { new PostParameter("id", id) }));
	}

	/**
	 * ???????
	 *
	 * @param status
	 *            ??????????????URLencode??????140???
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/update">statuses/update</a>
	 * @since JDK 1.5
	 */
	public Status UpdateStatus(String status) throws WeiboException {
		return new Status(client.post(WeiboConfig.getValue("baseURL")
				+ "statuses/update.json",
				new PostParameter[] { new PostParameter("status", status) }));
	}

	/**
	 * ???????
	 *
	 * @param status
	 *            ??????????????URLencode??????140???
	 * @param lat
	 *            ????????-90.0?+90.0?+????????0.0?
	 * @param long ????????-180.0?+180.0?+????????0.0?
	 * @param annotations
	 *            ?????????????????????????????????????????????????
	 *            ???json???????????????512????????????
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/update">statuses/update</a>
	 * @since JDK 1.5
	 */
	public Status UpdateStatus(String status, Float lat, Float longs,
			String annotations) throws WeiboException {
		return new Status(client.post(WeiboConfig.getValue("baseURL")
				+ "statuses/update.json", new PostParameter[] {
				new PostParameter("status", status),
				new PostParameter("lat", lat.toString()),
				new PostParameter("long", longs.toString()),
				new PostParameter("annotations", annotations) }));
	}

	/**
	 * ????????????
	 *
	 * @param status
	 *            ??????????????URLencode??????140???
	 * @param pic
	 *            ??????????JPEG?GIF?PNG?????????5M?
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/upload">statuses/upload</a>
	 * @since JDK 1.5
	 */
	public Status UploadStatus(String status, ImageItem item)
			throws WeiboException {
		return new Status(client.multPartURL(
				WeiboConfig.getValue("baseURL") + "statuses/upload.json",
				new PostParameter[] { new PostParameter("status", status)},
				item));
	}

	/**
	 * ????????????
	 *
	 * @param status
	 *            ??????????????URLencode??????140???
	 * @param pic
	 *            ??????????JPEG?GIF?PNG?????????5M?
	 * @param lat
	 *            ????????-90.0?+90.0?+????????0.0?
	 * @param long ????????-180.0?+180.0?+????????0.0?
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/upload">statuses/upload</a>
	 * @since JDK 1.5
	 */
	public Status UploadStatus(String status, ImageItem item, Float lat,
			Float longs) throws WeiboException {
		return new Status(client.multPartURL(
				WeiboConfig.getValue("baseURL") + "statuses/upload.json",
				new PostParameter[] { new PostParameter("status", status),
						new PostParameter("lat", lat.toString()),
						new PostParameter("long", longs.toString()) }, item));
	}

	/**
	 * ?????????????
	 *
	 * @return Emotion
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a href="http://open.weibo.com/wiki/2/emotions">emotions</a>
	 * @since JDK 1.5
	 */
	public List<Emotion> getEmotions() throws WeiboException {
		return Emotion.constructEmotions(client.get(WeiboConfig
				.getValue("baseURL") + "emotions.json"));
	}

	/**
	 * ?????????????
	 *
	 * @param type
	 *            ?????face??????ani??????cartoon?????????face
	 * @param language
	 *            ?????cnname????twname???????cnname
	 * @return Emotion
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version com.dengjintian.weibo2rss.weibo4j-V2 1.0.0
	 * @see <a href="http://open.weibo.com/wiki/2/emotions">emotions</a>
	 * @since JDK 1.5
	 */
	public List<Emotion> getEmotions(String type, String language)
			throws WeiboException {
		return Emotion.constructEmotions(client.get(
				WeiboConfig.getValue("baseURL") + "emotions.json",
				new PostParameter[] {
					new PostParameter("type", type),
					new PostParameter("language", language) }));
	}

}
