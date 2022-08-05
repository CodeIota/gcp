package gcp_api.gcp.replication.server;

import gcp_api.gcp.replication.shared.Message;

public interface ResponseEvent {
    public void notify(Message message);
}
