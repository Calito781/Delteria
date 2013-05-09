<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="delt" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<delt:page>

<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/knockout/knockout-2.2.1.js"></script>

<div class="box">
<h1>Player Card</h1>

<p style="text-align: center">
  <input type="text" data-bind="value: account" />
</p>

<p style="text-align: center">
  <img width="450px" height="80px" data-bind="attr:{src: img}" />
</p>

<p style="text-align: center">
<input type="text"  data-bind="value: img" disabled size="75" />
</p>

<hr />

<ul style="list-style: none; text-align: center; padding: 0px; margin: 0px;" data-bind="foreach: backgrounds">
  <li style="list-style: none; display: inline;">
    <img width="450px" height="80px" data-bind="attr:{src: 'game/image.png?filename=delt_card-backdrop' + $data + '.png'}, click: $parent.setBackground"/>
  </li>
</ul>

</div>

<script type="text/javascript">
var CardSystem = function(account) {
	var self = this;
	this.account = ko.observable(account);
	this.background = ko.observable(1);
	this.backgrounds = ko.observableArray([1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30]);
	
	this.img = ko.computed(function() {
		return "http://delteria.net:8383/card.png?account=" + this.account() + "&background=" + this.background();
	}, this);
	this.setBackground = function(background) {
		self.background(background);
	};
};

ko.applyBindings(new CardSystem("Delteria"));
</script>

</delt:page>
