var app = angular.module("delivery", ["checklist-model"], function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});

app.controller('pedidoController', function ($scope, $location, $http) {

    $scope.itens = [];
    $scope.subTotal = 0;
    $scope.pedidoItens = [];

    var carregarItens = function () {
        $http.get("/api/itens").success(function (data) {
            $scope.itens = data["_embedded"]["itens"];
        }).error(function (data, status) {
            $scope.message = "Aconteceu um problema: " + data;
        });
    };

    $scope.realizarPedido = function (cliente, pedidoItens) {
        $scope.message = "";
        var itensId = [];
        for (var i = 0; i < $scope.pedidoItens.length; i++)
            itensId.push($scope.pedidoItens[i].id);
        
        $scope.urlPedido = "/rest/pedido/novo";

        var data = {
            "clienteNome" : cliente,
            "itensId" : itensId
        }

        $http.post($scope.urlPedido, data).success(function (data) {
            $scope.idPedido = data["pedido"];
            $scope.precoTotal = data["precoTotal"];
            $scope.mensagem = data["mensagem"];
        }).error(function (data, status) {
            $scope.error = "Aconteceu um problema: "
                + "Status:" + data.status + " - error:" + data.error;
        });
    };

    $scope.selecionarItem = function () {

        if (this.checked)
            $scope.subTotal += this.i.preco;
        else
            $scope.subTotal -= this.i.preco;

    }

    carregarItens();

});