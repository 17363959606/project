package cn.demo.netty.protocoltcp;

import lombok.Getter;
import lombok.Setter;

/**
 * 协议包
 */
@Setter
@Getter
public class MessageProtocol {

    private int len;

    private byte[] content;
}
