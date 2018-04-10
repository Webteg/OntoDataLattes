var sizeList = parseInt(document.getElementById('sizeList').value);
var cont = 0;
function CriarFile(text) {
	  var element = document.createElement('a');
	  element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
	  element.setAttribute('download', 'Chave.Dtkey');
	  element.style.display = 'none';
	  document.body.appendChild(element);
	  element.click();
	  //document.body.removeChild(element);
	}
function Avancar() {
	if (cont < sizeList) {
		if (cont == 0) {
			document.getElementById('divItemInicial').classList.add("d-none");
		} else {
			document.getElementById('divItem' + (cont - 1)).classList
					.add("d-none");
		}
		cont++;
		document.getElementById('divItem' + (cont - 1)).classList
				.remove("d-none");
	} else {
		if (cont == sizeList+1) {
		} else {
			document.getElementById('divItem' + (cont - 1)).classList
					.add("d-none");
			cont++;
			document.getElementById('divItemChave').classList.remove("d-none");
			document.getElementById('botaoavancar').classList.add("d-none");
			
		}
	}
}
function Voltar() {
	if (cont != 0) {
		if (cont == (sizeList + 1)) {
			document.getElementById('divItemChave').classList.add("d-none");
			document.getElementById('botaoavancar').classList.remove("d-none");
			cont--;
			document.getElementById('divItem' + (cont - 1)).classList
					.remove("d-none");

		} else {
			document.getElementById('divItem' + (cont - 1)).classList
					.add("d-none");
			cont--;
			if (cont == 0) {
				document.getElementById('divItemInicial').classList
						.remove("d-none");
			} else {
				document.getElementById('divItem' + (cont - 1)).classList
						.remove("d-none");
			}
		}
	} else {
		javascript: window.history.go(-1);
	}

}
function ItemLimite(Nome, item) {
	var x = document.getElementById("myCheck" + Nome + "ItemLimite" + item).checked;
	var inputs = document.querySelectorAll("." + Nome + "limite" + item);
	if (x) {
		for (var i = 0; i < inputs.length; i++)
			inputs[i].classList.remove("d-none");
	} else {
		for (var i = 0; i < inputs.length; i++)
			inputs[i].classList.add("d-none");
	}
}
function ItemStatus(Nome, item) {
	var x = document.getElementById("myCheck" + Nome + "ItemStatus" + item).checked;
	var inputs = document.querySelectorAll("." + Nome + "Tipo1tt" + item);
	var xx = document.querySelectorAll("." + Nome + "Tipo0tt" + item);
	if (x) {
		for (var i = 0; i < inputs.length; i++)
			inputs[i].classList.remove("d-none");
		for (var j = 0; j < xx.length; j++)
			xx[j].classList.add("d-none");
	} else {
		for (var i = 0; i < inputs.length; i++)
			inputs[i].classList.add("d-none");
		for (var j = 0; j < xx.length; j++)
			xx[j].classList.remove("d-none");
	}
}
function OrienItemLimite(item) {
	ItemLimite("Orien", item);
}

function OrienItemStatus(item, valor) {
	var Nome = "Orien";
	var valor0 = document.querySelectorAll("." + Nome + "Tipo0tt" + item);
	var valor1 = document.querySelectorAll("." + Nome + "Tipo1tt" + item);
	var valor2 = document.querySelectorAll("." + Nome + "Tipo2tt" + item);
	var valor3 = document.querySelectorAll("." + Nome + "Tipo3tt" + item);
	switch (valor) {
	case 0:
		for (var i = 0; i < valor0.length; i++)
			valor0[i].classList.remove("d-none");
		for (var j = 0; j < valor1.length; j++)
			valor1[j].classList.add("d-none");
		for (var i = 0; i < valor2.length; i++)
			valor2[i].classList.add("d-none");
		for (var j = 0; j < valor3.length; j++)
			valor3[j].classList.add("d-none");
		break;
	case 1:
		for (var i = 0; i < valor0.length; i++)
			valor0[i].classList.add("d-none");
		for (var j = 0; j < valor1.length; j++)
			valor1[j].classList.remove("d-none");
		for (var i = 0; i < valor2.length; i++)
			valor2[i].classList.add("d-none");
		for (var j = 0; j < valor3.length; j++)
			valor3[j].classList.add("d-none");
		break;
	case 2:
		for (var i = 0; i < valor0.length; i++)
			valor0[i].classList.add("d-none");
		for (var j = 0; j < valor1.length; j++)
			valor1[j].classList.add("d-none");
		for (var i = 0; i < valor2.length; i++)
			valor2[i].classList.remove("d-none");
		for (var j = 0; j < valor3.length; j++)
			valor3[j].classList.add("d-none");
		break;
	case 3:
		for (var i = 0; i < valor0.length; i++)
			valor0[i].classList.add("d-none");
		for (var j = 0; j < valor1.length; j++)
			valor1[j].classList.add("d-none");
		for (var i = 0; i < valor2.length; i++)
			valor2[i].classList.add("d-none");
		for (var j = 0; j < valor3.length; j++)
			valor3[j].classList.remove("d-none");
		break;

	}
}

function GerarKey() {
	var key;
	key = "Ano>" + document.getElementById("AnoInicio").value + ">"
			+ document.getElementById("AnoFim").value + "%";
	// var NomeItemForAbre = [ "For", "PrTec", "PrArt", "PrBli", "Orien",
	// "Banc", "CrGe" ];
	for (var t = 0; t < 8; t++) {
		var formDiv = document.querySelectorAll('.indexCont' + t);
		if (formDiv.length > 0) {
			// alert(formDiv[0].getAttribute("id"));
			key = key + formDiv[0].getAttribute("id") + "!";
			for (var i = 0; i < formDiv.length; i++) {
				// var list = document.getElementById(i);
				var list = document.querySelectorAll('.'
						+ formDiv[0].getAttribute("id") + 'ItemIndex' + i);
				// alert(list.length);
				for (var k = 0; k < list.length; k++) {
					// alert(i);
					if (list[k] != null) {
						if (!(list[k].classList.contains("d-none"))) {
							var listId = list[k].getAttribute("name");
							var itemName = list[k].getAttribute("id");
							var ItemAbre = document
									.getElementsByClassName("NomeItem"
											+ itemName + "&" + t);
							// alert(ItemAbre.length)

							key = key + "#" + ItemAbre[0].getAttribute("name");
							var valor = document.querySelectorAll('.'
									+ formDiv[0].getAttribute("id") + 'Tipo'
									+ listId + 'valor' + i);
							var limite = document.querySelectorAll('.'
									+ formDiv[0].getAttribute("id") + 'Tipo'
									+ listId + 'limite' + i);
							// alert('.FormacaoTipo'+listId+'valor'+i);
							// alert(valor.length);
							for (var j = 0; j < valor.length; j++) {
								key = key + "@" + valor[j].getAttribute("name")
										+ "=" + valor[j].value + "=";
								if (!(limite[j].classList.contains("d-none"))) {
									key = key + "&" + limite[j].value + "&";
								}
							}
						}
					}
				}
			}
			key = key + "%";
		}
	}
	//document.getElementById("keyId").value = key;
	//document.getElementById("divItemChave").classList.remove("d-none");
	CriarFile(key);
}
