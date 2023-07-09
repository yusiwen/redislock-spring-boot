package cn.yusiwen.redislock.constant;

/**
 * Redis连接方式
 * <p>
 * 包含:
 * <li>standalone: 单节点部署方式</li>
 * <li>sentinel: 哨兵部署方式</li>
 * <li>cluster: 集群方式</li>
 * <li>masterslave: 主从部署方式</li>
 *
 * @author Siwen Yu (yusiwen@gmail.com)
 */
public enum RedisConnectionType {

    /**
     * 单节点部署方式
     */
    STANDALONE("standalone", "单节点部署方式"),
    /**
     * 哨兵部署方式
     */
    SENTINEL("sentinel", "哨兵部署方式"),
    /**
     * 哨兵部署方式
     */
    CLUSTER("cluster", "c"),
    /**
     * 主从部署方式
     */
    MASTERSLAVE("masterslave", "主从部署方式");

    /**
     * Type
     */
    private final String type;
    /**
     * Description
     */
    private final String description;

    RedisConnectionType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
