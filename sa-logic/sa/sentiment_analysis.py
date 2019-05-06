from textblob import TextBlob
from flask import Flask, request, jsonify

app = Flask(__name__)


@app.route("/analyse/sentiment", methods=['POST'])
def analyse_sentiment():
    sentence = request.get_json()['sentence']
    polarity = TextBlob(sentence).sentences[0].polarity
    return jsonify(
        sentence=sentence,
        polarity=polarity
    )

@app.route("/health", methods=['GET'])
def check_health():
    return jsonify(
        message="sa logic is healthy"
    )


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
