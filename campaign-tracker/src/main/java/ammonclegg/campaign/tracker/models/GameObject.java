package ammonclegg.campaign.tracker.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Interface common to all campaign items
 *
 * @author ammonclegg on 8/2/19.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY)
public interface GameObject {
  String getName();

  String getDescription();

  Campaign getCampaign();

  void setCampaign(Campaign campaign);
}
