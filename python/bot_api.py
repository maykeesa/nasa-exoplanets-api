import asyncio
from flask import Flask, request, jsonify
import BingImageCreator
import requests

app = Flask(__name__)

# Crie o loop de eventos aqui para usá-lo globalmente
loop = asyncio.get_event_loop()

# Endpoint Flask para enviar uma mensagem usando o bot
@app.route('/send-message', methods=['POST'])
def send_message():
    prompt = request.json.get('prompt')


    if not isinstance(prompt, str):
        return jsonify({"error": "Prompt deve ser uma string"}), 400

    # Codifique o prompt em bytes
    prompt_encoded = prompt.encode('utf-8')
    url_encoded_prompt = requests.utils.quote(prompt_encoded)

    cookie = "1dAdKfzIhN1e2eFlOGO-Klef7ZMhY5XndSTVT2sdy_AxvJyFaKuSO9YJPJzdFqhQ6pkZ_C2Fs9d6jRPPcUvCN8NGhkWU2Di8vavQIg0MAJj2XOgCc2gvqPBUx2V20Mn5JidnsvEef8yDuTZkjFfyIu4F8uTjpETjiO_sARcyeKao_aFoz2BlMFf9o0o3xfu7v2om80CmpYowffc1pKC0RTHMiYyj2d89xC3iW0NQx3Js"
    return jsonify(BingImageCreator.ImageGen(
        auth_cookie_SRCHHPGUSR=cookie,
        auth_cookie=cookie,
        debug_file='teste.txt',).get_images(prompt=url_encoded_prompt))

# Função para executar a API Flask em uma nova thread
def run_api():
    app.run(port=5000)

# Rodar o bot, a API Flask, e quaisquer outras funções que você queira no mesmo loop
try:
    loop.run_in_executor(None, run_api)
    loop.run_forever()
except KeyboardInterrupt:
    loop.close()
